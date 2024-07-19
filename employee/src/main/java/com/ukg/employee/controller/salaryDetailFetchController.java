package com.ukg.employee.controller;

import com.ukg.employee.dto.FetchSalaryDetailDto;
import com.ukg.employee.service.IsalaryDetailsFetch;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class salaryDetailFetchController {
    private final IsalaryDetailsFetch iSalaryDetailsFetch;

    @GetMapping("/fetchSalaryDetail")
    public ResponseEntity<FetchSalaryDetailDto> fetchSalaryDetail(@RequestParam Long salaryId) {
        FetchSalaryDetailDto fetchSalaryDetailDto = iSalaryDetailsFetch.fetchSalaryDetail(salaryId);
        return ResponseEntity.status(HttpStatus.OK).body(fetchSalaryDetailDto);
    }
}
