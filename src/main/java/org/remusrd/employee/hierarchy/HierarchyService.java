package org.remusrd.employee.hierarchy;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class HierarchyService {

    public Hierarchy retrieveEmployeeHierarchy(Map<String, String> employeeSupervisors) {
        return new Hierarchy(
                employeeSupervisors.entrySet()
                        .stream()
                        .map(it -> new Employee(it.getValue(), Collections.emptyList()))
                        .collect(Collectors.toUnmodifiableList())
        );
    }
}
