package com.ukg.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class SalaryDto {
    private Double basicSalary;
    private Double hra;
    private Double allowances;
}
