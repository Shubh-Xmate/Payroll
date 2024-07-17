package com.ukg.employee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDto1 {
    private String statusCode;
    private String message;
    private Long employeeId;
    private String mobileNumber;
}
