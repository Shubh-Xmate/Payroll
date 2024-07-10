package com.payroll.leave.service.impl;

import com.payroll.leave.dto.LeaveDetailsDto;
import com.payroll.leave.entity.LeaveDetails;
import com.payroll.leave.exception.EmployeeAlreadyExistsException;
import com.payroll.leave.exception.ResourceNotFoundException;
import com.payroll.leave.mapper.LeaveDetailsMapper;
import com.payroll.leave.repository.LeaveDetailsRepository;
import com.payroll.leave.service.ILeaveDetailsService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class LeaveDetailsImpl extends ILeaveDetailsService {

    private final LeaveDetailsRepository leaveDetailsRepository;

    @Override
    public void createAccount(Long employeeId) {
        Optional<LeaveDetails> foundLeaveDetails = leaveDetailsRepository.findByEmployeeId(employeeId);

        if(foundLeaveDetails.isPresent()) {
            throw new EmployeeAlreadyExistsException("Employee already exists with this employeeId" + employeeId);
        }

        LeaveDetails leaveDetails = new LeaveDetails();
        Long year = (long) LocalDateTime.now().getYear();

        leaveDetails.setEmployeeId(employeeId);
        leaveDetails.setLeaveYear(year);

        leaveDetailsRepository.save(leaveDetails);
    }

    @Override
    public LeaveDetailsDto fetchAccountDetails(Long employeeId, Long leaveYear) {
        LeaveDetails leaveDetails = leaveDetailsRepository.findByEmployeeIdAndLeaveYear(employeeId, leaveYear)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employee", "employeeId and leaveYear", String.valueOf(employeeId + leaveYear))
                );

        LeaveDetailsDto leaveDetailsDto = LeaveDetailsMapper.mapToLeaveDetailsDto(leaveDetails, new LeaveDetailsDto());
        return leaveDetailsDto;
    }

    @Override
    public boolean deleteAccount(Long employeeId, Long year) {
        return false;
    }

    @Override
    public boolean updateAccount(Long employeeId, Long year, LeaveDetailsDto leaveDetailsDto) {
        return false;
    }

}
