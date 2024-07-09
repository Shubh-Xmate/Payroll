package com.payroll.leave.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "leaveDetails")
@Data @AllArgsConstructor @NoArgsConstructor
public class LeaveDetails extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveDetailId;

    @NotNull(message = "Employee Id cannot be empty")
    private Long employeeId;

    @NotNull(message = "Employee Name Can not be empty")
    private String name;

    @NotNull(message = "Employee Email Can not be empty")
    private String email;

    @NotNull(message = "Employee Mobile number Can not be empty")
    private String mobileNumber;
}
