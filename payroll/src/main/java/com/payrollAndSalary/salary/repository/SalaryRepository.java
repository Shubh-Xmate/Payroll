package com.payrollAndSalary.salary.repository;

import com.payrollAndSalary.salary.entity.Salary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SalaryRepository extends JpaRepository<Salary,Long> {
    Optional<Salary> findBySalaryId(Long salaryId);


}