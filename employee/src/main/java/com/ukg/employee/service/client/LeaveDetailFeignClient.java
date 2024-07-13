package com.ukg.employee.service.client;
import com.ukg.employee.dto.FetchLeaveDetailDto;
import com.ukg.employee.dto.LeaveDetailsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("leave")
public interface LeaveDetailFeignClient {

    @GetMapping("/api/leaveDetails/fetch")
    public ResponseEntity<FetchLeaveDetailDto> getLeaves(@RequestParam Long employeeId, @RequestParam Long leaveYear);

}
