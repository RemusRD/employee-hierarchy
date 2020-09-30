package org.remusrd.employee.hierarchy.rest;

import org.remusrd.employee.hierarchy.Hierarchy;
import org.remusrd.employee.hierarchy.HierarchyService;
import org.remusrd.employee.hierarchy.rest.dto.HierarchyResponse;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HierarchyController {

    private final HierarchyService service;
    private final ConversionService conversionService;

    public HierarchyController(HierarchyService service, ConversionService conversionService) {
        this.service = service;
        this.conversionService = conversionService;
    }

    @PostMapping("/employees/hierarchy")
    public ResponseEntity<HierarchyResponse> createHierarchy(@RequestBody Map<String, String> employees) {
        final Hierarchy hierarchy = service.retrieveEmployeeHierarchy(employees);
        final HierarchyResponse hierarchyResponse = conversionService.convert(hierarchy, HierarchyResponse.class);
        return ResponseEntity.ok(hierarchyResponse);
    }
}
