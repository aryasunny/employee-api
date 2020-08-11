package com.farmiq.employee.rest;

import com.farmiq.employee.models.response.TeamResponseDto;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class RestClientForTeamAPI {
    public static TeamResponseDto getTeamById(int teamId) {
        final String uri = "http://localhost:8090/team/{teamId}";

        Map<String, String> params = new HashMap<String, String>();
        params.put("teamId", "1");

        RestTemplate restTemplate = new RestTemplate();
        TeamResponseDto result = restTemplate.getForObject(uri, TeamResponseDto.class, params);
        return result;
    }
}
