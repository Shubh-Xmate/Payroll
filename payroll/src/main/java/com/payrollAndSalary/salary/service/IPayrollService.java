package com.payrollAndSalary.salary.service;

import com.payrollAndSalary.salary.dto.PayrollDto;
import com.payrollAndSalary.salary.dto.SalaryDto;

import java.util.List;

public interface IPayrollService {
    PayrollDto createPayroll(Long employeeId, SalaryDto salaryDto);

    PayrollDto fetchPayrollDetails(Long employeeId, String payrollMonth, int payrollYear);

    List<PayrollDto> getEmployeesPayrolls();
}