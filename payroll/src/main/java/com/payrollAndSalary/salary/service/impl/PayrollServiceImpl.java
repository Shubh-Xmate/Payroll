package com.payrollAndSalary.salary.service.impl;

import com.payrollAndSalary.salary.dto.PayrollDto;
import com.payrollAndSalary.salary.dto.PaidLeaveDetailDto;
import com.payrollAndSalary.salary.entity.Payroll;
import com.payrollAndSalary.salary.entity.Salary;
import com.payrollAndSalary.salary.exception.PayrollAlreadyExistsException;
import com.payrollAndSalary.salary.exception.ResourceNotFoundException;
import com.payrollAndSalary.salary.mapper.PayrollMapper;
import com.payrollAndSalary.salary.repository.PayrollRepository;
import com.payrollAndSalary.salary.repository.SalaryRepository;
import com.payrollAndSalary.salary.service.IPayrollService;
import com.payrollAndSalary.salary.dto.PaidLeaveDetailDto;

import lombok.AllArgsConstructor;
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
//    private final SalaryRepository salaryRepository;

    @Override
    public void createPayroll(Long employeeId) {
//        Payroll payroll = new Payroll();

        // Automatically set month and year
        LocalDate now = LocalDate.now();
        String month = now.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
        int year = now.getYear();

        // Fetch salary details for the employee
//        Salary salary = salaryRepository.findByEmployeeId(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Salary", "employeeId", employeeId.toString()));

        // Calculate deductions and net salary
//        double hra = salary.getHra();
//        double allowance = salary.getAllowance();
//        double basicSalary = salary.getBasicSalary();

        // Mock salary details for the employee
        double basicSalary = 20000.0;
        double hra = 15000.0;
        double allowance = 5000.0;

        // Assume paidLeave is fetched from another branch
         double paidLeave= fetchPaidLeave(employeeId);
//        double paidLeave = PaidLeaveDetailDto.getpaidLeaves();
        double deductions = calculateDeductions(basicSalary, paidLeave, now);


        double netSalary = hra + allowance + basicSalary - deductions;

        PayrollDto payrollDto = new PayrollDto();
        payrollDto.setEmployeeId(employeeId);
        payrollDto.setPayrollMonth(month);
        payrollDto.setPayrollYear(year);
        payrollDto.setDeductions(deductions);
        payrollDto.setNetSalary(netSalary);

        Payroll payroll = PayrollMapper.mapToPayroll(payrollDto, new Payroll());
        payrollRepository.save(payroll);
    }

    private double fetchPaidLeave(Long employeeId) {
//
        return 2.0;
    }

    private double calculateDeductions(double basicSalary, double paidLeave, LocalDate date) {
//        if (paidLeave == 0) {
//            return 0.0;
//        }
//        YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonth());
//        int daysInMonth = yearMonth.lengthOfMonth();
//        return basicSalary / daysInMonth * paidLeave;
        return 6000.0;
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


