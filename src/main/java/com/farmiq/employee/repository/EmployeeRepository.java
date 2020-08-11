package com.farmiq.employee.repository;

import com.farmiq.employee.models.domain.EmployeeDomainModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDomainModel, Integer> {

    List<EmployeeDomainModel> getEmployeeDomainModelByFirstNameContains(String firstName);

    List<EmployeeDomainModel> getEmployeeDomainModelByLastNameContains(String lastName);

    List<EmployeeDomainModel> getEmployeeDomainModelByFirstNameContainsAndLastNameContains(String firstName,
                                                                                           String lastName);
}
