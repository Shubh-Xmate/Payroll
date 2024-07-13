package com.ukg.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchLeaveDetailDto {

    private Long employeeId;

    private Long remainingSickLeaves;

    private Long remainingCasualLeaves;

    private Long remainingEarnedLeaves;

    private Long leaveYear;

    private Long paidLeaves;

    private Long totalPaidLeaves;
}
