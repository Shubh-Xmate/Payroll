package com.ukg.employee.controller;

import com.ukg.employee.dto.EmployeeDto;
import com.ukg.employee.dto.ResponseDto;
import com.ukg.employee.service.IEmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {

    private final IEmployeeService iEmployeeService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        iEmployeeService.createEmployee(employeeDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto( "201",  "Created Successfully"));
        }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccount(@RequestParam
                                                         @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile Number should have ten digit")
                                                         String mobileNumber, @Valid @RequestBody EmployeeDto employeeDto) {
        boolean isUpdated = iEmployeeService.updateEmployeeDetails(mobileNumber, employeeDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("204", "Updated Successfully"));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto("500", "Internal server error"));
        }
    }
    @GetMapping("/fetch")
    public ResponseEntity<EmployeeDto> fetchAccount(@RequestParam
                                                        @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile Number should have ten digit")
                                                        String mobileNumber){
        EmployeeDto employeeDto = iEmployeeService.fetchEmployeeDetails(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(employeeDto);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam
                                                         @Pattern(regexp = "^$|[0-9]{10}", message = "Mobile Number should have ten digit")
                                                         String mobileNumber){
        boolean isDeleted = iEmployeeService.deleteEmployee(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto("204", "Deleted Successfully"));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto("500", "Internal server error"));
        }
    }
    @GetMapping("/fetchall")
    public ResponseEntity<List<EmployeeDto>> getAllCustomer(){
        List<EmployeeDto> employeeDtoList = iEmployeeService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(employeeDtoList);
    }
    @GetMapping("/hello")
    public ResponseEntity<String> helloworld() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello World");
    }
}
