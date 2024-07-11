package com.payrollAndSalary.salary.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "salary")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Salary extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salaryId;

    @NotNull(message = "Basic salary cannot be empty")
    private Double basicSalary;

    @NotNull(message = "HRA cannot be empty")
    private Double hra;

    @NotNull(message = "Allowances cannot be empty")
    private Double allowances;
}