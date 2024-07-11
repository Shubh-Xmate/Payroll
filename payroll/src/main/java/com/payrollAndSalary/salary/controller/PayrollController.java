package com.payrollAndSalary.salary.controller;

import com.payrollAndSalary.salary.dto.ResponseDto;
import com.payrollAndSalary.salary.dto.PayrollDto;
import com.payrollAndSalary.salary.entity.Payroll;
import com.payrollAndSalary.salary.service.IPayrollService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Valid
@RestController
@RequestMapping("api/payroll")
@AllArgsConstructor
public class PayrollController {
    private final IPayrollService iPayrollService;

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello World");
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createPayroll(@RequestBody PayrollDto payrollDto) {
        iPayrollService.createPayroll(payrollDto.getEmployeeId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Created successfully"));
    }

    @GetMapping("/fetch")
    public ResponseEntity<PayrollDto> fetchPayroll(@RequestParam Long employeeId, @RequestParam String payrollMonth, @RequestParam int payrollYear) {
        PayrollDto payrollDto = iPayrollService.fetchPayrollDetails(employeeId, payrollMonth, payrollYear);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(payrollDto);
    }
}




