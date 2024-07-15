package com.payrollAndSalary.salary.service.clients;

import com.payrollAndSalary.salary.dto.EmployeeDto;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("employee")
public interface EmployeeFeignClient {
    @GetMapping("/api/fetchall")
    ResponseEntity<List<EmployeeDto>> fetchAllEmployees();

    @GetMapping("/api/fetch")
    ResponseEntity<EmployeeDto> fetchAccount(@RequestParam
                                             @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile Number should have ten digit")
                                             String mobileNumber);
}