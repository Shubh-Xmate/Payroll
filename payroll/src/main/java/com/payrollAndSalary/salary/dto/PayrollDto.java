package com.payrollAndSalary.salary.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor

public class PayrollDto {
    private Long employeeId;
    private String payrollMonth;
    private int payrollYear;
    private Double deductions;
    private Double netSalary;
//    private SalaryDto salaryDto;
}