package org.remusrd.employee.hierarchy.rest;

import org.remusrd.employee.hierarchy.Hierarchy;
import org.remusrd.employee.hierarchy.HierarchyCommandService;
import org.remusrd.employee.hierarchy.HierarchyQueryService;
import org.remusrd.employee.hierarchy.rest.dto.HierarchyResponse;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class HierarchyController {

    private final HierarchyCommandService commandService;
    private final HierarchyQueryService queryService;
    private final ConversionService conversionService;

    public HierarchyController(HierarchyCommandService commandService, HierarchyQueryService queryService, ConversionService conversionService) {
        this.commandService = commandService;
        this.queryService = queryService;
        this.conversionService = conversionService;
    }

    @PostMapping("/employees/hierarchy")
    public ResponseEntity<HierarchyResponse> createHierarchy(@RequestBody Map<String, String> employees) {
        final Hierarchy hierarchy = commandService.retrieveEmployeeHierarchy(employees);
        final HierarchyResponse hierarchyResponse = conversionService.convert(hierarchy, HierarchyResponse.class);
        return ResponseEntity.ok(hierarchyResponse);
    }

    @GetMapping("/employees/{name}/supervisors")
    public ResponseEntity<HierarchyResponse> query(@PathVariable String name) {
        final Hierarchy hierarchy = queryService.getEmployeeSupervisors(name);
        final HierarchyResponse hierarchyResponse = conversionService.convert(hierarchy, HierarchyResponse.class);
        return ResponseEntity.ok(hierarchyResponse);
    }
}

