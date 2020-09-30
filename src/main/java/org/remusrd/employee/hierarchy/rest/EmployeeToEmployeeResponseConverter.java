package org.remusrd.employee.hierarchy.rest;

import org.remusrd.employee.hierarchy.Employee;
import org.remusrd.employee.hierarchy.rest.dto.EmployeeResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EmployeeToEmployeeResponseConverter implements Converter<Employee, EmployeeResponse> {
    @Override
    public EmployeeResponse convert(Employee employee) {
        final var subordinates =
                employee.getSubordinates()
                        .stream().map(this::convert)
                        .collect(Collectors.toUnmodifiableList());

        return new EmployeeResponse(employee.getName(), subordinates);
    }
}
