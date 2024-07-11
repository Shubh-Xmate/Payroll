package com.payrollAndSalary.salary.mapper;

import com.payrollAndSalary.salary.dto.SalaryDto;
import com.payrollAndSalary.salary.entity.Salary;

public class SalaryMapper {
    public static SalaryDto mapToSalaryDto(Salary salary, SalaryDto salaryDto) {
        salaryDto.setBasicSalary(salary.getBasicSalary());
        salaryDto.setHra(salary.getHra());
        salaryDto.setAllowances(salary.getAllowances());
        return salaryDto;
    }

    public static Salary mapToSalary(SalaryDto salaryDto, Salary salary) {
        salary.setBasicSalary(salaryDto.getBasicSalary());
        salary.setHra(salaryDto.getHra());
        salary.setAllowances(salaryDto.getAllowances());
        return salary;
    }
}