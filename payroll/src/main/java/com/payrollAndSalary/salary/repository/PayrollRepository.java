package com.payrollAndSalary.salary.repository;

import com.payrollAndSalary.salary.entity.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll,Long> {
    Optional<Payroll> findByEmployeeId(Long employeeId);
    Optional<Payroll> findByEmployeeIdAndPayrollMonthAndPayrollYear(Long employeeId, String payrollMonth, int payrollYear);
}