package com.ukg.employee.service;

import com.ukg.employee.dto.EmployeeDto;

import java.util.List;

public interface IEmployeeService {

    Long createEmployee(EmployeeDto employeeDto);

    EmployeeDto fetchEmployeeDetails(String mobileNumber);

    boolean deleteEmployee(String mobileNumber);

    boolean updateEmployeeDetails(String mobileNumber, EmployeeDto employeeDto);

    List<EmployeeDto> getAll();

    EmployeeDto fetchEmployeeByIdDetails(Long employeeId);
}
