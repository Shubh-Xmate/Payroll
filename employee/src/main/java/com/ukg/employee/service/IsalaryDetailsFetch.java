package com.ukg.employee.service;

import com.ukg.employee.dto.FetchSalaryDetailDto;

public interface IsalaryDetailsFetch {
    FetchSalaryDetailDto fetchSalaryDetail(Long salaryId);
}
