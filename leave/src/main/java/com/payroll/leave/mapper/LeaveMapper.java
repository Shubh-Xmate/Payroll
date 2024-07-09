package com.payroll.leave.mapper;

import com.payroll.leave.dto.LeaveDto;
import com.payroll.leave.entity.Leave;


public class LeaveMapper {

    public static LeaveDto mapToCustomerDto(Leave leave, LeaveDto leaveDto ){

        return leaveDto;
    }

    public static Leave mapToCustomer(LeaveDto leaveDto, Leave leave ){

        return leave;
    }
}

