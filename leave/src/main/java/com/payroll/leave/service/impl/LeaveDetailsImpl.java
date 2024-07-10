package com.payroll.leave.service.impl;

import com.payroll.leave.dto.LeaveDetailsDto;
import com.payroll.leave.service.ILeaveDetailsService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class LeaveDetailsImpl extends ILeaveDetailsService {

    @Override
    public void createAccount(LeaveDetailsDto leaveDetailsDto) {

    }

    @Override
    public LeaveDetailsDto fetchAccountDetails(Long employeeId, Long year) {
        return null;
    }

    @Override
    public boolean deleteAccount(Long employeeId, Long year) {
        return false;
    }

    @Override
    public boolean updateAccount(Long employeeId, Long year, LeaveDetailsDto leaveDetailsDto) {
        return false;
    }

}
