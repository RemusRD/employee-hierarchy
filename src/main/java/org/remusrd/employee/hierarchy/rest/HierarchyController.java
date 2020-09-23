package org.remusrd.employee.hierarchy.rest;

import org.remusrd.employee.hierarchy.Hierarchy;
import org.remusrd.employee.hierarchy.HierarchyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class HierarchyController {

    public final HierarchyService service;

    public HierarchyController(HierarchyService service) {
        this.service = service;
    }

    @PostMapping("/employees/supervisors")
    public ResponseEntity<String> getHello(@RequestBody Map<String,String> employees) {
        final Hierarchy hierarchy = service.retrieveEmployeeHierarchy(employees);
        return ResponseEntity.ok(hierarchy.toString());
    }
}
