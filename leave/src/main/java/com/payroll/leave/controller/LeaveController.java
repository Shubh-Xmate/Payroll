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
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class LeaveController {

    private final ILeaveService iLeaveService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createLeave(@RequestBody @Valid LeaveDto leaveDto){
        boolean isCreated = iLeaveService.createLeaveRequest(leaveDto, leaveDto.getEmployeeId());
        if(isCreated){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto("200", "leave Request created successfully"));
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto("400", "Enter Valid dates and valid entries"));
        }

    }

    @GetMapping("/fetchall")
    public ResponseEntity<List<LeaveDto>> getLeaves(@RequestParam Long employeeId){ // change this to mobileNumber
        List<LeaveDto> leaveDtoList = iLeaveService.getAllLeave(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(leaveDtoList);
    }

    @GetMapping("/fetchallbyManagerId")
    public ResponseEntity<List<LeaveDto>> getLeavesbyManagerId(@RequestParam Long managerId){ // change this to mobileNumber
        List<LeaveDto> leaveDtoList = iLeaveService.getAllLeaveByManagerId(managerId);
        return ResponseEntity.status(HttpStatus.OK).body(leaveDtoList);
    }

    @PutMapping("/changestatus")
    public ResponseEntity<ResponseDto> changeStatus(@RequestParam Long leaveId, @RequestParam String status, @RequestBody LeaveDto leaveDto){
        iLeaveService.changeLeaveStatus(leaveId, status, leaveDto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("200", "status changed succesfully"));
    }

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.status(HttpStatus.OK).body("Hello Shailesh here kay kartos");
    }
}
