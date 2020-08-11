package com.farmiq.employee.integrationtests;

import com.farmiq.employee.client.EmployeeApplication;
import com.farmiq.employee.config.IntegrationTestsH2JPAConfig;
import com.farmiq.employee.models.domain.EmployeeDomainModel;
import com.farmiq.employee.models.response.TeamResponseDto;
import com.farmiq.employee.repository.EmployeeRepository;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.ResponseEntity;

import java.net.URL;

@SpringBootTest(classes = {EmployeeApplication.class, IntegrationTestsH2JPAConfig.class}, webEnvironment =
        SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 8090)
public class EmployeeIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    public void getEmployees_should_return_one_record() throws Exception {

        // Arrange
        // Setting up an Employee
        EmployeeDomainModel employeeDomainModel = new EmployeeDomainModel(1, "Eddy",
                null, "Kelly", "13231212331", "eddy.kelly@farmiq.co.nz", 1);

        // setting up expected response
        String expectedResponse = "[{\n" +
                "\t\"employee_id\": 1,\n" +
                "\t\"firstName\": \"Eddy\",\n" +
                "\t\"middleName\": null,\n" +
                "\t\"lastName\": \"Kelly\",\n" +
                "\t\"email\": \"13231212331\",\n" +
                "\t\"phone\": \"eddy.kelly@farmiq.co.nz\",\n" +
                "\t\"team_name\": \"Falafelot\"\n" +
                "}]";

        // Saving Data to In-memory H2 Database
        employeeRepository.save(employeeDomainModel);

        // Stubbing WireMock
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/team/1"))
                .willReturn(WireMock.aResponse().withHeader("Content-Type", "application/json")
                        .withBody(String.valueOf(new JSONObject(new TeamResponseDto("Falafelot"))))));

        // Act
        ResponseEntity<String> response = restTemplate.getForEntity(
                new URL("http://localhost:" + port + "/employees").toString(), String.class);

        // Assert
        JSONAssert.assertEquals(expectedResponse, String.valueOf(response.getBody()), false);
    }
}
