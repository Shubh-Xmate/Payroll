package com.payrollAndSalary.salary.controller;

import com.payrollAndSalary.salary.dto.PaidLeaveDetailDto;
import com.payrollAndSalary.salary.service.IPaidLeaveDetail;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class PaidLeaveDetailController {
    private final IPaidLeaveDetail ipaidLeaveDetail;

    @GetMapping("/fetchPaidLeaveDetail")
    public ResponseEntity<PaidLeaveDetailDto> fetchPaidLeaveDetail(@RequestParam Long employeeId){
        PaidLeaveDetailDto paidLeaveDetailDto= ipaidLeaveDetail.fetchPaidLeaveDetail(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(paidLeaveDetailDto);
    }

}
