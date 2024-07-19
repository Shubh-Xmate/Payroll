package com.payroll.leave.service;

import com.payroll.leave.dto.LeaveDto;

import java.util.List;

public interface ILeaveService {

    boolean createLeaveRequest(LeaveDto leaveDto, Long employeeId);
    List<LeaveDto> getAllLeave(Long employeeId);
    List<LeaveDto> getAllLeaveByManagerId(Long managerId);
    void changeLeaveStatus(Long leaveId, String status, LeaveDto leaveDto);

}
