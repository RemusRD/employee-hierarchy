package org.remusrd.employee.hierarchy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class HierarchyServiceTest {

    private HierarchyService sut;

    @BeforeEach
    void setUp() {
        sut = new HierarchyService();
    }

    @Test
    void givenASetOfEmployeesAndSupervisorsShouldMapTheHierarchy() {
        final Map<String, String> employeeSupervisors = Map.of("Pete", "Nick");

        final Hierarchy hierarchy = sut.retrieveEmployeeHierarchy(employeeSupervisors);

        assertThat(hierarchy.getSupervisor().getName()).isEqualTo("Nick");
    }
}
