package com.payrollAndSalary.salary.service;

import com.payrollAndSalary.salary.dto.PaidLeaveDetailDto;

public interface IPaidLeaveDetail {
    PaidLeaveDetailDto fetchPaidLeaveDetail(Long employeeId);
}
