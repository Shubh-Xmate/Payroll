package com.payroll.leave.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class LeaveDetailsDto {

    private Long employeeId;

    private String name;

    private String email;

    private String mobileNumber;
}
