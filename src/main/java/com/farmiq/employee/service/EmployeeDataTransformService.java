package com.farmiq.employee.service;

import com.farmiq.employee.models.domain.EmployeeDomainModel;
import com.farmiq.employee.models.response.EmployeeResponseDto;
import com.farmiq.employee.rest.RestClientForTeamAPI;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeDataTransformService {
    public List<EmployeeResponseDto> getEmployeeResponseDtoFromDomainModels(List<EmployeeDomainModel> employees) {
        return employees.stream().map((employee) -> new EmployeeResponseDto(employee.getEmployee_id(),
                employee.getFirstName(), employee.getMiddleName(), employee.getLastName(), employee.getEmail(),
                employee.getPhone(),
                getTeamName(employee.getTeam_id()))).collect(Collectors.toList());
    }

    private String getTeamName(int teamId) {
        return RestClientForTeamAPI.getTeamById(teamId).getTeamName();
    }
}
