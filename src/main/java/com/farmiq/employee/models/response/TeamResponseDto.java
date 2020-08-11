package com.farmiq.employee.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class TeamResponseDto {
    private String teamName;

    public TeamResponseDto() {
    }

    public TeamResponseDto(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
