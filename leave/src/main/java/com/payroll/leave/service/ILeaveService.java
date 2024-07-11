package com.payroll.leave.service;

import com.payroll.leave.dto.LeaveDto;

import java.util.List;

public interface ILeaveService {

    boolean createLeaveRequest(LeaveDto leaveDto);
    List<LeaveDto> getAllLeave(Long employeeId);
    void changeLeaveStatus(Long leaveId, String status, LeaveDto leaveDto);

}
