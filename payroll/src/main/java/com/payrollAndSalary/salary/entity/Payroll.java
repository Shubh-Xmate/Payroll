package com.payrollAndSalary.salary.entity;

import com.payrollAndSalary.salary.dto.SalaryDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "payroll")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Payroll extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long payrollId;


    private Long employeeId;


    private String payrollMonth;


    private int payrollYear;

    private Double deductions;
    private Double netSalary;
}