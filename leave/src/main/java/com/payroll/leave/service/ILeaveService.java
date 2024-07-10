package com.payroll.leave.service;

import com.payroll.leave.dto.LeaveDto;

import java.util.List;

public interface ILeaveService {

    void createLeaveRequest(LeaveDto leaveDto);
    List<LeaveDto> getAllLeave(Long employeeId);






//    void createAccount(CustomerDto customerDto);
//    CustomerDto getAccountDetails(String mobileNumber);
//    boolean updateAccount(String mobileNumber, CustomerDto customerDto);
//    boolean deleteAccountMethod(String mobileNumber);
//    List<CustomerDto> getAllAccount();
}
