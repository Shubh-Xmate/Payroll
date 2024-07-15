package com.ukg.employee.service.impl;

import com.ukg.employee.dto.FetchLeaveDetailDto;
import com.ukg.employee.dto.LeaveDetailsDto;
import com.ukg.employee.repository.EmployeeRepository;
import com.ukg.employee.service.IleaveDetailsFetch;
import com.ukg.employee.service.client.LeaveDetailFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class fetchLeaveDetail implements IleaveDetailsFetch {
    private final EmployeeRepository employeeRepository;
    private  final  LeaveDetailFeignClient leaveDetailFeignClient;

    @Override
    public FetchLeaveDetailDto fetchLeaveDetail(Long employeeId) {
        Long leaveYear = (long) LocalDateTime.now().getYear();
        ResponseEntity<FetchLeaveDetailDto> leaveDtoResponseEntity = leaveDetailFeignClient.getLeaves(employeeId, leaveYear);

        return leaveDtoResponseEntity.getBody();

    }
}
