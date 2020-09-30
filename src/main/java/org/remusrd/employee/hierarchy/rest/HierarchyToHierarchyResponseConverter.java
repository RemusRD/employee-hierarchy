package org.remusrd.employee.hierarchy.rest;

import org.remusrd.employee.hierarchy.Hierarchy;
import org.remusrd.employee.hierarchy.rest.dto.HierarchyResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class HierarchyToHierarchyResponseConverter implements Converter<Hierarchy, HierarchyResponse> {

    private final EmployeeToEmployeeResponseConverter responseConverter;

    public HierarchyToHierarchyResponseConverter(EmployeeToEmployeeResponseConverter responseConverter) {
        this.responseConverter = responseConverter;
    }

    @Override
    public HierarchyResponse convert(Hierarchy hierarchy) {
        return new HierarchyResponse(responseConverter.convert(hierarchy.getSupervisor()));
    }
}
