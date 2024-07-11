package com.payrollAndSalary.salary.service.impl;

import com.payrollAndSalary.salary.dto.SalaryDto;
import com.payrollAndSalary.salary.entity.Salary;
import com.payrollAndSalary.salary.exception.ResourceNotFoundException;
import com.payrollAndSalary.salary.exception.SalaryAlreadyExistsException;
import com.payrollAndSalary.salary.mapper.SalaryMapper;
import com.payrollAndSalary.salary.repository.SalaryRepository;
import com.payrollAndSalary.salary.service.ISalaryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SalaryServiceImpl implements ISalaryService {
    private SalaryRepository salaryRepository;

    @Override
    public void createSalary(SalaryDto salaryDto) {
//        Optional<Salary> foundSalary = salaryRepository.findBySalaryId(salaryId);
//        if(foundSalary.isPresent()) {
//            throw new SalaryAlreadyExistsException("Salary already exists for this salary id");
//        }
        Salary salary = SalaryMapper.mapToSalary(salaryDto,new Salary());
        salaryRepository.save(salary);
    }

    @Override
    public SalaryDto fetchSalaryDetails(Long salaryId) {
        Salary foundSalary = salaryRepository.findBySalaryId(salaryId).orElseThrow(
                () -> new ResourceNotFoundException("Salary","salaryId",salaryId)
        );
        SalaryDto salaryDto = SalaryMapper.mapToSalaryDto(foundSalary, new SalaryDto());
        return salaryDto;
    }

    @Override
    public boolean updateSalary(Long salaryId, SalaryDto salaryDto) {
        boolean isUpdated = false;

        Salary salary = salaryRepository.findBySalaryId(salaryId).orElseThrow(
                () -> new ResourceNotFoundException("Salary","salaryId",salaryId)
        );

        if(salary!=null)
        {
            Salary updatedSalary = SalaryMapper.mapToSalary(salaryDto,salary);
            salaryRepository.save(updatedSalary);

            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteSalary(Long salaryId)
    {
        boolean isDeleted = false;
        Salary foundSalary = salaryRepository.findBySalaryId(salaryId).orElseThrow(
                () -> new ResourceNotFoundException("Salary","salaryId",salaryId)
        );
        if(foundSalary!=null) {
            salaryRepository.delete(foundSalary);
            isDeleted=true;
        }
        return isDeleted;
    }

    @Override
    public List<SalaryDto> fetchAllSalaries() {
        List<Salary> salaries = salaryRepository.findAll();
        List<SalaryDto> salaryDtos = new ArrayList<>();
        for(Salary salary:salaries) {
            SalaryDto salaryDto = SalaryMapper.mapToSalaryDto(salary,new SalaryDto());

            salaryDtos.add(salaryDto);
        }
        return salaryDtos;
    }
}