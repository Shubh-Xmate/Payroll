package com.payroll.leave.service.clients;

import com.payroll.leave.dto.EmployeeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("employee")
public interface EmployeeFeignClient {


    @GetMapping("/api/fetchById")
    ResponseEntity<EmployeeDto> fetchAccount(@RequestParam Long employeeId);
}
