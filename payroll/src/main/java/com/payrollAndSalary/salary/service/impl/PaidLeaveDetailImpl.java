package com.payrollAndSalary.salary.service.impl;

import com.netflix.discovery.converters.Auto;
import com.payrollAndSalary.salary.dto.LeaveDetailsDto;
import com.payrollAndSalary.salary.entity.Payroll;
import com.payrollAndSalary.salary.dto.PayrollDto;
import com.payrollAndSalary.salary.dto.PaidLeaveDetailDto;
import com.payrollAndSalary.salary.exception.ResourceNotFoundException;
import com.payrollAndSalary.salary.mapper.PayrollMapper;
import com.payrollAndSalary.salary.repository.PayrollRepository;
import com.payrollAndSalary.salary.repository.SalaryRepository;
import com.payrollAndSalary.salary.service.IPaidLeaveDetail;
import com.payrollAndSalary.salary.service.client.LeaveFeignClient;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class PaidLeaveDetailImpl implements IPaidLeaveDetail {

    private final PayrollRepository payrollRepository;
    private final SalaryRepository salaryRepository;
    private final LeaveFeignClient leaveFeignClient;

    @Override
    public PaidLeaveDetailDto fetchPaidLeaveDetail(Long employeeId) {

//        Payroll payroll = payrollRepository.findById(employeeId)
//                .orElseThrow(
//                () -> new ResourceNotFoundException("Payroll", "employeeId", employeeId)
//        );

        Payroll payroll = new Payroll();
        payroll.setEmployeeId(employeeId);

        PaidLeaveDetailDto paidLeaveDetailDto = PayrollMapper.mapToPaidLeaveDetailDto(payroll,new PaidLeaveDetailDto());

        Long leaveYear = (long) LocalDateTime.now().getYear();
        ResponseEntity<LeaveDetailsDto> leaveDtoResponseEntity = leaveFeignClient.getLeaves(employeeId, leaveYear);

        paidLeaveDetailDto.setLeaveDetailsDto(leaveDtoResponseEntity.getBody());
//
//        paidLeaveDetailDto.setLeaveDto(leaveDtoResponseEntity.getBody());


        return paidLeaveDetailDto;
    }
}
