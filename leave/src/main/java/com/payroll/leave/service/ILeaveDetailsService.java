package com.payroll.leave.service;

import com.payroll.leave.dto.LeaveDetailsDto;

public abstract class ILeaveDetailsService {

    public abstract void createAccount(LeaveDetailsDto leaveDetailsDto);

    public abstract LeaveDetailsDto fetchAccountDetails(Long employeeId, Long year);

    public abstract boolean deleteAccount(Long employeeId, Long year);

    public abstract boolean updateAccount(Long employeeId, Long year, LeaveDetailsDto leaveDetailsDto);
}
