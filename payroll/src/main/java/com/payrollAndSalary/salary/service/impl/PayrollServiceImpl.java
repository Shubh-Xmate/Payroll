package com.payrollAndSalary.salary.service.impl;

import com.payrollAndSalary.salary.dto.EmployeeDto;
import com.payrollAndSalary.salary.dto.PayrollDto;
import com.payrollAndSalary.salary.dto.SalaryDto;

import com.payrollAndSalary.salary.dto.PaidLeaveDetailDto;
import com.payrollAndSalary.salary.entity.Payroll;
import com.payrollAndSalary.salary.entity.Salary;
import com.payrollAndSalary.salary.exception.PayrollAlreadyExistsException;
import com.payrollAndSalary.salary.exception.ResourceNotFoundException;
import com.payrollAndSalary.salary.mapper.PayrollMapper;
import com.payrollAndSalary.salary.repository.PayrollRepository;
import com.payrollAndSalary.salary.repository.SalaryRepository;
import com.payrollAndSalary.salary.service.IPayrollService;
import com.payrollAndSalary.salary.service.ISalaryService;
import com.payrollAndSalary.salary.service.clients.EmployeeFeignClient;
import com.payrollAndSalary.salary.dto.PaidLeaveDetailDto;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PayrollServiceImpl implements IPayrollService {
    private final PayrollRepository payrollRepository;
    private final EmployeeFeignClient employeeFeignClient;
    private final ISalaryService iSalaryService;


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

        // Assume paidLeave is fetched from another branch
//        double paidLeave = fetchPaidLeave(employeeId); // not present now
        double paidLeave = 1.0;
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

    private double calculateDeductions(double basicSalary, double paidLeave, LocalDate date) {
        int numberOfDaysInMonth = YearMonth.of(date.getYear(), date.getMonth()).lengthOfMonth();
        return (basicSalary / numberOfDaysInMonth) * paidLeave;
    }


    @Override
    public PayrollDto fetchPayrollDetails(Long employeeId, String month, int year) {
        Payroll foundPayroll = payrollRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Payroll", "employeeId",employeeId)
                );
        PayrollDto payrollDto = PayrollMapper.mapToPayrollDto(foundPayroll, new PayrollDto());
        return payrollDto;
    }
}


