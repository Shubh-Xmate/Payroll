package com.payroll.leave.mapper;

import com.payroll.leave.dto.LeaveDetailsDto;
import com.payroll.leave.entity.LeaveDetails;

public class LeaveDetailsMapper {

    public static LeaveDetailsDto mapToLeaveDetailsDto(LeaveDetails leaveDetails, LeaveDetailsDto leaveDetailsDto) {
        leaveDetailsDto.setEmployeeId(leaveDetails.getEmployeeId());
        leaveDetailsDto.setEmail(leaveDetails.getEmail());
        leaveDetailsDto.setName(leaveDetails.getName());
        leaveDetailsDto.setMobileNumber(leaveDetails.getMobileNumber());

        return leaveDetailsDto;
    }

    public static LeaveDetails mapToLeaveDetails(LeaveDetailsDto leaveDetailsDto, LeaveDetails leaveDetails) {
        leaveDetails.setEmployeeId(leaveDetailsDto.getEmployeeId());
        leaveDetails.setEmail(leaveDetailsDto.getEmail());
        leaveDetails.setName(leaveDetailsDto.getName());
        leaveDetails.setMobileNumber(leaveDetailsDto.getMobileNumber());

        return leaveDetails;
    }

}
