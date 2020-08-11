package com.farmiq.employee.service;

import com.farmiq.employee.models.domain.EmployeeDomainModel;
import com.farmiq.employee.models.request.SearchCriteria;
import com.farmiq.employee.models.response.EmployeeResponseDto;
import com.farmiq.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableScheduling
public class EmployeeDataRetrievalService {

    final EmployeeRepository employeeRepository;

    final EmployeeDataTransformService employeeDataTransformService;

    @Autowired
    public EmployeeDataRetrievalService(EmployeeRepository employeeRepository,
                                        EmployeeDataTransformService employeeDataTransformService) {
        this.employeeRepository = employeeRepository;
        this.employeeDataTransformService = employeeDataTransformService;
    }

    public List<EmployeeResponseDto>getEmployeesBasedOnSearchCriteria(SearchCriteria searchCriteria) {
        if (searchCriteriaProvided(searchCriteria)) {

// TODO:: This below code should work, should not have to write all those IF else, not sure why it's not working,
//  something to sort for future

//            EmployeeDomainModel employeeDomainModel = new EmployeeDomainModel();
//            employeeDomainModel.setFirstName(searchCriteria.getFirstName());
//            employeeDomainModel.setLastName(searchCriteria.getLastName());
//            Example exampleOfEmployeeDomainModel = Example.of(employeeDomainModel);
//            return employeeDataTransformService.getEmployeeResponseDtoFromDomainModels(
//                    employeeRepository.findAll(exampleOfEmployeeDomainModel));

            if (onlyFirstNameProvided(searchCriteria)) {
                List<EmployeeDomainModel> employees =
                        employeeRepository.getEmployeeDomainModelByFirstNameContains(searchCriteria.getFirstName());
                return employeeDataTransformService.getEmployeeResponseDtoFromDomainModels(employees);
            } else if (onlyLastNameProvided(searchCriteria)) {
                return employeeDataTransformService.getEmployeeResponseDtoFromDomainModels(
                        employeeRepository.getEmployeeDomainModelByLastNameContains(searchCriteria.getLastName()));
            } else {
                return employeeDataTransformService.getEmployeeResponseDtoFromDomainModels(
                        employeeRepository.getEmployeeDomainModelByFirstNameContainsAndLastNameContains(
                                searchCriteria.getFirstName(), searchCriteria.getLastName()));
            }
        } else {
            return employeeDataTransformService.getEmployeeResponseDtoFromDomainModels(employeeRepository.findAll());
        }
    }

    private boolean onlyFirstNameProvided(SearchCriteria searchCriteria) {
        return searchCriteria.getFirstName() != null && searchCriteria.getLastName() == null;
    }

    private boolean onlyLastNameProvided(SearchCriteria searchCriteria) {
        return searchCriteria.getFirstName() == null && searchCriteria.getLastName() != null;
    }

    private boolean searchCriteriaProvided(SearchCriteria searchCriteria) {
        return (searchCriteria.getFirstName() != null || searchCriteria.getLastName() != null) ? true : false;
    }
}
