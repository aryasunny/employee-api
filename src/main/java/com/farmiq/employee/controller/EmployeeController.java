package com.farmiq.employee.controller;

import com.farmiq.employee.models.request.SearchCriteria;
import com.farmiq.employee.models.response.EmployeeResponseDto;
import com.farmiq.employee.service.EmployeeDataRetrievalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    private EmployeeDataRetrievalService employeeDataRetrievalService;

    @Autowired
    public EmployeeController(EmployeeDataRetrievalService employeeDataRetrievalService) {
        this.employeeDataRetrievalService = employeeDataRetrievalService;
    }

    @GetMapping("/employees")
    public List<EmployeeResponseDto> getEmployeesByNames(SearchCriteria searchCriteria) {
        return employeeDataRetrievalService.getEmployeesBasedOnSearchCriteria(searchCriteria);
    }

}
