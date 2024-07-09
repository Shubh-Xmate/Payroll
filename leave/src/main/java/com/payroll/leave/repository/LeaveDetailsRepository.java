package com.payroll.leave.repository;

import com.payroll.leave.entity.LeaveDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveDetailsRepository extends JpaRepository<LeaveDetails, Long> {


}
