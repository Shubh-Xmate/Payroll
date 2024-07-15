package com.ukg.employee.service.client;

import com.ukg.employee.dto.FetchSalaryDetailDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("payroll")
public interface SalaryDetailFeignClient {
    @GetMapping("/api/salary/fetch")
    public ResponseEntity<FetchSalaryDetailDto> getSalary(@RequestParam Long salaryId);
}
