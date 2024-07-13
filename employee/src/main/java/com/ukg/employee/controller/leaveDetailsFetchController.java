package com.ukg.employee.controller;
import com.ukg.employee.dto.FetchLeaveDetailDto;
import com.ukg.employee.dto.LeaveDetailsDto;
import com.ukg.employee.service.IleaveDetailsFetch;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class leaveDetailsFetchController {
    private final IleaveDetailsFetch ileavedetailsfetch;

    @GetMapping("/fetchLeaveDetail")
    public ResponseEntity<FetchLeaveDetailDto> fetchLeaveDetail(@RequestParam Long employeeId){
        FetchLeaveDetailDto fetchLeaveDetailDto = ileavedetailsfetch.fetchLeaveDetail(employeeId);
        return ResponseEntity.status(HttpStatus.OK).body(fetchLeaveDetailDto);

    }

}
