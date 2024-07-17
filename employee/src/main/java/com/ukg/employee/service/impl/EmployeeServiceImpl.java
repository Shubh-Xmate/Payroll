package com.ukg.employee.service.impl;

import com.ukg.employee.dto.EmployeeDto;
import com.ukg.employee.entity.Employee;
import com.ukg.employee.exception.EmployeeAlreadyExistsException;
import com.ukg.employee.exception.ResourceNotFoundByIdException;
import com.ukg.employee.exception.ResourceNotFoundException;
import com.ukg.employee.mapper.EmployeeMapper;
import com.ukg.employee.repository.EmployeeRepository;
import com.ukg.employee.service.IEmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements IEmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public Long createEmployee(EmployeeDto employeeDto) {
        Optional<Employee> foundEmployee = employeeRepository.findByMobileNumber(employeeDto.getMobileNumber());

        if (foundEmployee.isPresent()) {
            throw new EmployeeAlreadyExistsException("Employee already exists for employeeId = " + foundEmployee.get().getEmployeeId() + " and Mobile Number = " + foundEmployee.get().getMobileNumber());
        }

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto, new Employee());
        employeeRepository.save(employee);
        return employee.getEmployeeId();
    }


    @Override
    public EmployeeDto fetchEmployeeDetails(String mobileNumber) {
        Employee foundEmployee = employeeRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "mobileNumber", mobileNumber)
        );

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(foundEmployee, new EmployeeDto());
        return employeeDto;
    }

    @Override
    public EmployeeDto fetchEmployeeByIdDetails(Long employeeId) {
        Employee foundEmployee = employeeRepository.findByEmployeeId(employeeId).orElseThrow(
                () -> new ResourceNotFoundByIdException("Employee", "employeeId", employeeId)
        );

        EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(foundEmployee, new EmployeeDto());
        return employeeDto;
    }

    @Override
    public boolean updateEmployeeDetails(String mobileNumber, EmployeeDto employeeDto) {
        boolean isUpdated = false;
        Employee employee = employeeRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "mobileNumber", mobileNumber)
        );

        if (employee != null) {
            Employee updatedEmployee = EmployeeMapper.mapToEmployee(employeeDto, employee);
            employeeRepository.save(updatedEmployee);
            isUpdated = true;
        }
        return isUpdated;

    }


    public boolean deleteEmployee(String mobileNumber) {
        boolean isDeleted = false;

        Employee employee = employeeRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "mobileNumber", mobileNumber)
        );
        if (employee != null) {
            employeeRepository.deleteById(employee.getEmployeeId());
            isDeleted = true;
        }
        return isDeleted;
    }
//    @Override
    public List<EmployeeDto> getAll(){
        List<Employee> employeeList = employeeRepository.findAll();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        for(Employee employee : employeeList){
            EmployeeDto employeeDto = EmployeeMapper.mapToEmployeeDto(employee, new EmployeeDto());
            employeeDtoList.add(employeeDto);
        }
        return employeeDtoList;

    }
}
