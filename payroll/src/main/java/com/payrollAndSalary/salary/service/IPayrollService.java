package com.payrollAndSalary.salary.service;

import com.payrollAndSalary.salary.dto.PayrollDto;
import java.util.List;

public interface IPayrollService {
    void createPayroll(Long employeeId);

    PayrollDto fetchPayrollDetails(Long employeeId, String payrollMonth, int payrollYear);

}