package org.remusrd.employee.hierarchy;

import org.remusrd.employee.hierarchy.persistence.HierarchyRepositoryAdapter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toUnmodifiableList;

@Component
public class HierarchyCommandService {
    private final HierarchyRepository repository;

    public HierarchyCommandService(HierarchyRepository repository) {
        this.repository = repository;
    }

    public Hierarchy retrieveEmployeeHierarchy(Map<String, String> employeeSupervisors) {
        final Map<String, List<String>> employeesBySupervisor = groupEmployeesBySupervisor(employeeSupervisors);
        final String topSupervisorName = getTopSupervisorName(employeeSupervisors);

        final var hierarchy = createEmployeeHierarchy(topSupervisorName, employeesBySupervisor);
        repository.save(hierarchy);

        return hierarchy;
    }

    private Hierarchy createEmployeeHierarchy(String topSupervisorName, Map<String, List<String>> employeesBySupervisor) {
        final HashMap<String, Employee> processedEmployees = new HashMap<>();
        return new Hierarchy(mapEmployee(employeesBySupervisor, processedEmployees, topSupervisorName, emptyList()));
    }

    private String getTopSupervisorName(Map<String, String> employeesBySupervisor) {
        //FIXME: improve the exceptions thrown and handle them
        final Map.Entry<String, String> anyEmployee = employeesBySupervisor.entrySet().stream().findFirst().orElseThrow(IllegalArgumentException::new);
        String supervisor = anyEmployee.getKey();
        while (employeesBySupervisor.containsKey(supervisor)) {
            supervisor = employeesBySupervisor.get(supervisor);
        }
        return supervisor;
    }

    private Map<String, List<String>> groupEmployeesBySupervisor(Map<String, String> employeeSupervisors) {
        final Map<String, List<String>> employeesBySupervisor = employeeSupervisors.entrySet()
                .stream()
                .collect(
                        groupingBy(
                                Map.Entry::getValue,
                                mapping(Map.Entry::getKey, toUnmodifiableList()))
                );
        return employeesBySupervisor;
    }

    private Employee mapEmployee(Map<String, List<String>> employeeSupervisors, Map<String, Employee> processedEmployees, String employeeName, List<String> supervisorsName) {
        if (processedEmployees.containsKey(employeeName)) {
            return processedEmployees.get(employeeName);
        }

        final List<String> subordinates = employeeSupervisors.get(employeeName) == null ? emptyList() : employeeSupervisors.get(employeeName);


        final Employee employee = subordinates
                .stream()
                .map(it -> mapEmployee(employeeSupervisors, processedEmployees, it, addSupervisorName(employeeName, supervisorsName)))
                .collect(collectingAndThen(toUnmodifiableList(), listOfEmployees -> new Employee(employeeName, listOfEmployees, supervisorsName)));

        processedEmployees.put(employeeName, employee);

        return employee;
    }

    private List<String> addSupervisorName(String employeeName, List<String> supervisorsName) {
        return supervisorsName == null ? List.of(employeeName) : Stream.concat(supervisorsName.stream(), Stream.of(employeeName)).collect(toUnmodifiableList());
    }

}
