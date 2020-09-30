package org.remusrd.employee.hierarchy.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.remusrd.employee.hierarchy.EmployeeHierarchyApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = EmployeeHierarchyApplication.class,
        webEnvironment = WebEnvironment.RANDOM_PORT)
class HierarchyControllerIT {

    public static final String USERNAME = "test";
    public static final String PASSWORD = "test";
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private static final String HOST = "http://localhost:";
    private HttpHeaders headers;

    @BeforeEach
    void setUp() {
        restTemplate = restTemplate.withBasicAuth(USERNAME, PASSWORD);
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    void createHierarchyShouldReturnTheCorrectHierarchyResult() {
        final HttpEntity<String> hierarchyRequest = new HttpEntity<>("{\n" +
                "    \"Pete\": \"Nick\",\n" +
                "    \"Barbara\": \"Nick\",\n" +
                "    \"Nick\": \"Sophie\",\n" +
                "    \"Sophie\": \"Jonas\"\n" +
                "} ", headers);

        final ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity(HOST + port + "/employees/hierarchy", hierarchyRequest, String.class);

        //FIXME: change to created when storing to the db
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        //FIXME: check for object content when json is returned as expected
    }
}
