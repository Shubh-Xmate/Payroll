package com.ukg.employee.mapper;

import com.ukg.employee.dto.EmployeeDto;
import com.ukg.employee.entity.Employee;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee, EmployeeDto employeeDto){
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setMobileNumber(employee.getMobileNumber());
        employeeDto.setDob(employee.getDob());
        employeeDto.setManagerId(employee.getManagerId());
        employeeDto.setDateOfJoining(employee.getDateOfJoining());
        employeeDto.setRoleId(employee.getRoleId());
        employeeDto.setSalaryId(employee.getSalaryId());
        employeeDto.setEmployeeId(employee.getEmployeeId());
        return employeeDto;
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto, Employee employee){
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setMobileNumber(employeeDto.getMobileNumber());
        employee.setDob(employeeDto.getDob());
        employee.setManagerId(employeeDto.getManagerId());
        employee.setDateOfJoining(employeeDto.getDateOfJoining());
        employee.setRoleId(employeeDto.getRoleId());
        employee.setSalaryId(employeeDto.getSalaryId());
        employee.setEmployeeId(employeeDto.getEmployeeId());
        return employee;
    }
}
