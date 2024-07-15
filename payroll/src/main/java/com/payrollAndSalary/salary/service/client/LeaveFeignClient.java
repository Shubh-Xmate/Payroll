package com.payrollAndSalary.salary.service.client;

import com.payrollAndSalary.salary.dto.LeaveDetailsDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("leave")
public interface LeaveFeignClient {

    @GetMapping("/api/leaveDetails/fetch")
    public ResponseEntity<LeaveDetailsDto> getLeaves(@RequestParam Long employeeId, @RequestParam Long leaveYear);

}


