package com.payroll.leave.controller;

import com.payroll.leave.dto.LeaveDto;
import com.payroll.leave.dto.ResponseDto;
import com.payroll.leave.service.ILeaveService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/leave")
@AllArgsConstructor
public class LeaveController {

    private final ILeaveService iLeaveService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLeave(@RequestBody @Valid LeaveDto leaveDto){
        iLeaveService.createLeaveRequest(leaveDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto("200", "leave Request created successfully"));
    }

    @GetMapping("/fetchall")
    public ResponseEntity<List<LeaveDto>> getLeaves(@RequestParam Long employeeId){ // change this to mobileNumber
        List<LeaveDto> leaveDtoList = iLeaveService.getAllLeave(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(leaveDtoList);
    }
}
