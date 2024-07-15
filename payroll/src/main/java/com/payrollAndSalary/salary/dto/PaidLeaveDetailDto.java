package com.payrollAndSalary.salary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaidLeaveDetailDto {
    private Long employeeId;
    private LeaveDetailsDto leaveDetailsDto;
}
