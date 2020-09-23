package org.remusrd.employee.hierarchy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

class HierarchyServiceTest {

    private HierarchyService sut;

    @BeforeEach
    void setUp() {
        sut = new HierarchyService();
    }

    @Test
    void givenASetOfEmployeesAndSupervisorsShouldMapTheHierarchy() {
        final Map<String, String> employeeSupervisors = Map.of(
                "Pete", "Nick",
                "Barbara", "Nick",
                "Nick", "Sophie",
                "Sophie", "Jonas"
        );

        final Hierarchy hierarchy = sut.retrieveEmployeeHierarchy(employeeSupervisors);

        final Employee supervisor = hierarchy.getSupervisor();
        assertThat(supervisor.getName()).isEqualTo("Jonas");
        final Employee firstSubordinate = supervisor.getSubordinates().get(0);
        assertThat(firstSubordinate.getName()).isEqualTo("Sophie");
        final Employee secondSubordinate = firstSubordinate.getSubordinates().get(0);
        assertThat(secondSubordinate.getName()).isEqualTo("Nick");
        assertThat(secondSubordinate.getSubordinates()).containsAll( List.of(new Employee("Pete", emptyList()), new Employee("Barbara", emptyList())));
    }
}
