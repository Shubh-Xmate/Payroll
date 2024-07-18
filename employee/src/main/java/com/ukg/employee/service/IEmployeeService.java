package com.ukg.employee.service;

import com.ukg.employee.dto.EmployeeDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IEmployeeService {

    @Transactional
    void createEmployee(EmployeeDto employeeDto);

    EmployeeDto fetchEmployeeDetails(String mobileNumber);

    boolean deleteEmployee(String mobileNumber);

    boolean updateEmployeeDetails(String mobileNumber, EmployeeDto employeeDto);

    List<EmployeeDto> getAll();

    EmployeeDto fetchEmployeeByIdDetails(Long employeeId);
}
