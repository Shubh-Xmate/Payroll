package com.ukg.employee.service.impl;

import com.ukg.employee.dto.FetchSalaryDetailDto;

import com.ukg.employee.service.IsalaryDetailsFetch;
import com.ukg.employee.service.client.SalaryDetailFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class fetchSalaryDetailImpl implements IsalaryDetailsFetch{
    private final SalaryDetailFeignClient salaryDetailFeignClient;

    @Override
    public FetchSalaryDetailDto fetchSalaryDetail(Long salaryId) {
        ResponseEntity<FetchSalaryDetailDto> responseEntity = salaryDetailFeignClient.getSalary(salaryId);
        return responseEntity.getBody();
    }
}
