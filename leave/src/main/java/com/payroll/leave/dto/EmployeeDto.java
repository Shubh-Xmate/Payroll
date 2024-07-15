package com.payroll.leave.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private LocalDate dob;
    private Long managerId;
    private String roleId;
    private LocalDate dateOfJoining;
    private Long salaryId;
    private Long EmployeeId;
//    private LeaveDetailsDto leaveDetailsDto;
//    private PayrollDto payrollDto;
//    private SalaryDto salaryDto;
//    private List<LeaveRequestDto> leaveRequestDtoList;
}
