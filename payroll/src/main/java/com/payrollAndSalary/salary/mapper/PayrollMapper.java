package com.payrollAndSalary.salary.mapper;

import com.payrollAndSalary.salary.dto.PayrollDto;
import com.payrollAndSalary.salary.entity.Payroll;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

public class PayrollMapper {

    public static PayrollDto mapToPayrollDto(Payroll payroll, PayrollDto payrollDto) {
        payrollDto.setEmployeeId(payroll.getEmployeeId());
        payrollDto.setPayrollMonth(payroll.getPayrollMonth());
        payrollDto.setPayrollYear(payroll.getPayrollYear());
        payrollDto.setDeductions(payroll.getDeductions());
        payrollDto.setNetSalary(payroll.getNetSalary());
        return payrollDto;
    }

    public static Payroll mapToPayroll(PayrollDto payrollDto, Payroll payroll) {
        payroll.setEmployeeId(payrollDto.getEmployeeId());
        payroll.setPayrollMonth(payrollDto.getPayrollMonth());
        payroll.setPayrollYear(payrollDto.getPayrollYear());
        payroll.setDeductions(payrollDto.getDeductions());
        payroll.setNetSalary(payrollDto.getNetSalary());
        return payroll;
    }
}
