package com.payrollAndSalary.salary.service;

import com.payrollAndSalary.salary.dto.PaidLeaveDetailDto;
import com.payrollAndSalary.salary.dto.PayrollDto;
import com.payrollAndSalary.salary.dto.SalaryDto;
import com.payrollAndSalary.salary.entity.Payroll;

import java.util.List;
import java.util.Optional;

public interface IPayrollService {
    PayrollDto createPayroll(Long employeeId, SalaryDto salaryDto);

    PayrollDto fetchPayrollDetails(Long employeeId, String payrollMonth, int payrollYear);

    List<PayrollDto> getEmployeesPayrolls();

    PayrollDto createSinglePayroll(String mobileNumber);
}