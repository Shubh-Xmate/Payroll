package com.payroll.message.dto;

public record LeaveMsgDto(Long employeeId, String startDate, String endDate, String leaveType, String leaveStatus, String mobileNumber) {
}
