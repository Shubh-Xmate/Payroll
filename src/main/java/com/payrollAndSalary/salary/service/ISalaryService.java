package com.payrollAndSalary.salary.service;

import com.payrollAndSalary.salary.dto.SalaryDto;
import com.payrollAndSalary.salary.entity.Salary;

import java.util.List;

public interface ISalaryService {
    void createSalary(SalaryDto salaryDto);

    SalaryDto fetchSalaryDetails(Long salaryId);

    boolean updateSalary(Long salaryId, SalaryDto salaryDto);

    boolean deleteSalary(Long salaryId);

    List<SalaryDto> fetchAllSalaries();
}