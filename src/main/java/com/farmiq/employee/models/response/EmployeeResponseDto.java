package com.farmiq.employee.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class EmployeeResponseDto {

    private int employee_id;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private String phone;

    private String team_name;

    public EmployeeResponseDto(int employee_id, String firstName, String middleName, String lastName, String email,
                               String phone, String team_name) {
        this.employee_id = employee_id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.team_name = team_name;
    }

    public EmployeeResponseDto() {
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    @Override
    public String toString() {
        return "EmployeeResponseDto{" +
                "employee_id=" + employee_id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", team_id=" + team_name +
                '}';
    }
}
