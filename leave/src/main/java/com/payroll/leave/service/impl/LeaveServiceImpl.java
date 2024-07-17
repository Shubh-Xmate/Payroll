package com.payroll.leave.service.impl;


import com.payroll.leave.dto.EmployeeDto;
import com.payroll.leave.dto.LeaveDetailsDto;
import com.payroll.leave.dto.LeaveDto;
import com.payroll.leave.entity.Leave;
import com.payroll.leave.entity.LeaveDetails;
import com.payroll.leave.exception.ResourceNotFoundException;
import com.payroll.leave.mapper.LeaveMapper;
import com.payroll.leave.repository.LeaveDetailsRepository;
import com.payroll.leave.repository.LeaveRepository;
import com.payroll.leave.service.ILeaveDetailsService;
import com.payroll.leave.service.clients.EmployeeFeignClient;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.hibernate.metamodel.internal.EmbeddableInstantiatorPojoIndirecting;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import com.payroll.leave.service.ILeaveService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class LeaveServiceImpl implements ILeaveService {

    private LeaveRepository leaveRepository;
    private LeaveDetailsRepository leaveDetailsRepository;
    private ILeaveDetailsService iLeaveDetailsService;
    private final EmployeeFeignClient employeeFeignClient;
    private final StreamBridge streamBridge;

    @Override
    public boolean createLeaveRequest(LeaveDto leaveDto, Long employeeId) {
        boolean isCreated = false;
        Long leaveYear = (long) LocalDate.now().getYear();

        EmployeeDto employeeDto = employeeFeignClient.fetchAccount(employeeId).getBody();
        leaveDto.setManagerId(employeeDto.getManagerId());
        LeaveDetailsDto leaveDetailsDto = iLeaveDetailsService.fetchAccountDetails(employeeDto.getEmployeeId(), leaveYear);
        if(validLeave(leaveDto, leaveDetailsDto)){
            System.out.println("Shailesh");
            Leave leave = LeaveMapper.mapToLeave(leaveDto, new Leave());
            leaveRepository.save(leave);
            isCreated = true;
        }
        return isCreated;
    }

    public static long countOfficeDays(LocalDate startDate, LocalDate endDate) {
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        long officeDaysCount = 0;

        for (long i = 0; i <= daysBetween; i++) {
            LocalDate currentDate = startDate.plusDays(i);
            DayOfWeek dayOfWeek = currentDate.getDayOfWeek();
            if (dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY) {
                officeDaysCount++;
            }
        }

        return officeDaysCount;
    }

    public static boolean validLeave(LeaveDto leaveDto, LeaveDetailsDto leaveDetailsDto){
        if (leaveDto.getLeaveType() != null) {
            long officeDays = countOfficeDays(leaveDto.getStartDate(), leaveDto.getEndDate());
            boolean isLeaveValid = false;

            switch (leaveDto.getLeaveType().toLowerCase()) {
                case "sick":
                    isLeaveValid = officeDays <= leaveDetailsDto.getRemainingSickLeaves();
                    break;
                case "casual":
                    isLeaveValid = officeDays <= leaveDetailsDto.getRemainingCasualLeaves();
                    break;
                case "earned":
                    isLeaveValid = officeDays <= leaveDetailsDto.getRemainingEarnedLeaves();
                    break;
                default:
                    break;
            }

            return isLeaveValid;
        }
        return false;

    }



    @Override
    public List<LeaveDto> getAllLeave(Long employeeId) {
        List<Leave> leaveList = leaveRepository.findAllByEmployeeId(employeeId);
        return leaveList.stream().map(leave
                -> LeaveMapper.mapToLeaveDto(leave, new LeaveDto())).toList();
    }

    @Override
    public void changeLeaveStatus( Long leaveId, String status, LeaveDto leaveDto) {
        Leave leave = leaveRepository.findById(leaveId).orElseThrow(() ->
                new ResourceNotFoundException("Leave", "leaveId", leaveId.toString()));
        leaveDto.setStatus(status);
        LeaveDetails leaveDetails = leaveDetailsRepository.findByEmployeeId(leave.getEmployeeId()).orElseThrow(() ->
                new ResourceNotFoundException("LeaveDetails", "EmployeeId", leaveDto.getEmployeeId().toString()));
        Leave updatedLeave = LeaveMapper.mapToLeave(leaveDto, leave);
        if(status.equals("APPROVED")){
            long officeDays = countOfficeDays(leave.getStartDate(), leave.getEndDate());
            decrementLeaveDetails(leave.getLeaveType(), leaveDetails, officeDays);
        }
        else{
            return;
        }
        leaveDetailsRepository.save(leaveDetails);
        leaveRepository.save(updatedLeave);

    }

    public static void decrementLeaveDetails(String leaveType, LeaveDetails leaveDetails, Long officeDays){
        switch (leaveType.toLowerCase()) {
            case "sick":
                leaveDetails.setRemainingSickLeaves(leaveDetails.getRemainingSickLeaves() - officeDays);
                break;
            case "casual":
                leaveDetails.setRemainingCasualLeaves(leaveDetails.getRemainingCasualLeaves() - officeDays);
                break;
            case "earned":
                leaveDetails.setRemainingEarnedLeaves(leaveDetails.getRemainingEarnedLeaves() - officeDays);
                break;
            default:
                leaveDetails.setPaidLeaves(leaveDetails.getPaidLeaves() + officeDays);
                break;
        }
    }


}
