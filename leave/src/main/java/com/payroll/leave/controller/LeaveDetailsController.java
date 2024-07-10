package com.payroll.leave.controller;

import com.payroll.leave.dto.LeaveDetailsDto;
import com.payroll.leave.dto.LeaveDto;
import com.payroll.leave.dto.ResponseDto;
import com.payroll.leave.repository.LeaveDetailsRepository;
import com.payroll.leave.service.ILeaveDetailsService;
import com.payroll.leave.service.ILeaveService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaveDetails")
@AllArgsConstructor
public class LeaveDetailsController {

    private final ILeaveDetailsService iLeaveDetailsService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLeaveDetailsAccount(@RequestBody LeaveDetailsDto leaveDetailsDto){
        iLeaveDetailsService.createAccount(leaveDetailsDto.getEmployeeId());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto("200", "leave details created successfully for employeeId" + leaveDetailsDto.getEmployeeId()));
    }

    @GetMapping("/fetch")
    public ResponseEntity<LeaveDetailsDto> getLeaves(@RequestParam Long employeeId, @RequestParam Long leaveYear){ // change this to mobileNumber
        LeaveDetailsDto leaveDetailsDto = iLeaveDetailsService.fetchAccountDetails(employeeId, leaveYear);
        return ResponseEntity.status(HttpStatus.OK).body(leaveDetailsDto);
    }
}
