package com.payrollAndSalary.salary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PayrollAlreadyExistsException extends RuntimeException{
    public PayrollAlreadyExistsException(String message) {
        super(message);
    }
}