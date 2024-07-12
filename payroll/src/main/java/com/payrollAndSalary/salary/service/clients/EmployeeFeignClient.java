package com.payrollAndSalary.salary.service.clients;

import com.payrollAndSalary.salary.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("employee")
public interface EmployeeFeignClient {
    @GetMapping("/api/fetchall")
    ResponseEntity<List<EmployeeDto>> fetchAllEmployees();
}