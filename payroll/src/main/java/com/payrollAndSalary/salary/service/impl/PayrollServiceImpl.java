package com.payrollAndSalary.salary.service.impl;

import com.payrollAndSalary.salary.dto.*;

import com.payrollAndSalary.salary.entity.Payroll;
import com.payrollAndSalary.salary.exception.PayrollAlreadyExistsException;
import com.payrollAndSalary.salary.exception.ResourceNotFoundException;
import com.payrollAndSalary.salary.mapper.PayrollMapper;
import com.payrollAndSalary.salary.repository.PayrollRepository;
import com.payrollAndSalary.salary.service.IPaidLeaveDetail;
import com.payrollAndSalary.salary.service.IPayrollService;
import com.payrollAndSalary.salary.service.ISalaryService;
import com.payrollAndSalary.salary.service.clients.EmployeeFeignClient;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.*;

@Service
@AllArgsConstructor
public class PayrollServiceImpl implements IPayrollService {
    private final PayrollRepository payrollRepository;
    private final EmployeeFeignClient employeeFeignClient;
    private final ISalaryService iSalaryService;
    private final IPaidLeaveDetail iPaidLeaveDetail;


    @Override
    public List<PayrollDto> getEmployeesPayrolls() {
        ResponseEntity<List<EmployeeDto>> employeeDtoListResponseEntity = employeeFeignClient.fetchAllEmployees();

        List<EmployeeDto> employeeDtoList = employeeDtoListResponseEntity.getBody();
        List<PayrollDto> payrollDtoList = new ArrayList<>();
        if(employeeDtoList != null) {
            for (EmployeeDto employeeDto : employeeDtoList) {
                SalaryDto salaryDto = iSalaryService.fetchSalaryDetails(employeeDto.getSalaryId());
                PayrollDto payrollDto = createPayroll(employeeDto.getEmployeeId(), salaryDto);
                payrollDtoList.add(payrollDto);
            }
        } else {
            throw new ResourceNotFoundException("Employee","employees",0L);
        }

        return payrollDtoList;
    }
    @Override
    public PayrollDto createPayroll(Long employeeId,SalaryDto salaryDto) {

        // Automatically get month and year
        LocalDate now = LocalDate.now();
        String month = now.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int year = now.getYear();

        if(DoesPayrollExist(employeeId,month,year)){
            throw new PayrollAlreadyExistsException("Payroll already exists");
        }
        // Assume paidLeave is fetched from another branch
        double paidLeave = fetchPaidLeave(employeeId);
//        double paidLeave = 2.0;
        double deductions = calculateDeductions(salaryDto.getBasicSalary(), paidLeave, now);



        double netSalary = salaryDto.getHra() + salaryDto.getBasicSalary() + salaryDto.getAllowances() - deductions;

        PayrollDto payrollDto = new PayrollDto();
        payrollDto.setEmployeeId(employeeId);
        payrollDto.setPayrollMonth(month);
        payrollDto.setPayrollYear(now.getYear());
        payrollDto.setDeductions(deductions);
        payrollDto.setNetSalary(netSalary);

        Payroll payroll = PayrollMapper.mapToPayroll(payrollDto, new Payroll());
        payrollRepository.save(payroll);
        return payrollDto;

    }

    public PayrollDto createSinglePayroll(String mobileNumber) {
        ResponseEntity<EmployeeDto> employeeDtoResponseEntity = employeeFeignClient.fetchAccount(mobileNumber);
        EmployeeDto employeeDto = employeeDtoResponseEntity.getBody();
        SalaryDto salaryDto = iSalaryService.fetchSalaryDetails(employeeDto.getSalaryId());
        return createPayroll(employeeDto.getEmployeeId(),salaryDto);
    }

    private double fetchPaidLeave(Long employeeId) {
        PaidLeaveDetailDto paidLeaveDetailDto = iPaidLeaveDetail.fetchPaidLeaveDetail(employeeId);
        LeaveDetailsDto leaveDetailsDto = paidLeaveDetailDto.getLeaveDetailsDto();

        if (leaveDetailsDto != null) {
            return leaveDetailsDto.getPaidLeaves();
        } else {
            throw new ResourceNotFoundException("Leave details", "employeeId", employeeId);
        }
    }

    private double calculateDeductions(double basicSalary, double paidLeave, LocalDate date) {
        int numberOfDaysInMonth = YearMonth.of(date.getYear(), date.getMonth()).lengthOfMonth();
        return (basicSalary / numberOfDaysInMonth) * paidLeave;
    }

    public boolean DoesPayrollExist(Long employeeId,String month,int year){
        if(payrollRepository.findByEmployeeIdAndPayrollMonthAndPayrollYear(employeeId,month,year).isPresent())
        {
            return true;
        }
        return false;
    }

    @Override
    public PayrollDto fetchPayrollDetails(Long employeeId, String month, int year) {
        Payroll foundPayroll = payrollRepository.findByEmployeeIdAndPayrollMonthAndPayrollYear(employeeId,month,year)
                .orElseThrow(() -> new ResourceNotFoundException("Payroll", "employeeId",employeeId)
                );
        PayrollDto payrollDto = PayrollMapper.mapToPayrollDto(foundPayroll, new PayrollDto());
        return payrollDto;
    }
}