package org.remusrd.employee.hierarchy.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.remusrd.employee.hierarchy.Employee;
import org.remusrd.employee.hierarchy.EmployeeFactory;
import org.remusrd.employee.hierarchy.rest.dto.EmployeeResponse;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

class EmployeeToEmployeeResponseConverterTest {
    private EmployeeToEmployeeResponseConverter sut;

    @BeforeEach
    void setUp() {
        sut = new EmployeeToEmployeeResponseConverter();
    }

    @Test
    void shouldConvert() {
        final Employee employee = EmployeeFactory.simpleEmployee();

        final EmployeeResponse result = sut.convert(employee);

        assertThat(result.getName()).isEqualTo("Jonas");
        final EmployeeResponse sophie = result.getSubordinates().get(0);
        assertThat(sophie.getName()).isEqualTo("Sophie");
        assertThat(sophie.getSubordinates())
                .isEqualTo(List.of(new EmployeeResponse("Pete", emptyList()), new EmployeeResponse("Barbara",emptyList())));

    }

}
