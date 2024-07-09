package com.payrollAndSalary.salary.controller;

import com.payrollAndSalary.salary.dto.ResponseDto;
import com.payrollAndSalary.salary.dto.SalaryDto;
import com.payrollAndSalary.salary.entity.Salary;
import com.payrollAndSalary.salary.service.ISalaryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Valid
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class SalaryController {
    private final ISalaryService iSalaryService;

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello World");
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createSalary(@Valid @RequestParam Long salaryId, @RequestBody SalaryDto salaryDto) {
        iSalaryService.createSalary(salaryDto,salaryId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201","Created successfully"));
    }

    @GetMapping("/fetch")
    public ResponseEntity<SalaryDto> fetch(@RequestParam Long salaryId) {
        SalaryDto salaryDto=iSalaryService.fetchSalaryDetails(salaryId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(salaryDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> update(@Valid @RequestParam Long salaryId, @RequestBody SalaryDto salaryDto){
        boolean isUpdated = iSalaryService.updateSalary(salaryId,salaryDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("204", "Updated successfully"));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto("500", "Internal server error"));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> delete(@RequestParam Long salaryId){
        Boolean isDeleted = iSalaryService.deleteSalary(salaryId);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("200", "Deleted successfully"));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto("500", "Internal server error"));
        }
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<SalaryDto>> fetchAll() {
        List<SalaryDto> salaryDtos=iSalaryService.fetchAllSalaries();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(salaryDtos);
    }
}