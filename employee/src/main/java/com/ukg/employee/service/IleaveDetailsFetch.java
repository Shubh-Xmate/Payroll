package com.ukg.employee.service;

import com.ukg.employee.dto.FetchLeaveDetailDto;
import com.ukg.employee.dto.LeaveDetailsDto;

public interface IleaveDetailsFetch {
    FetchLeaveDetailDto fetchLeaveDetail(Long employeeId);
}
