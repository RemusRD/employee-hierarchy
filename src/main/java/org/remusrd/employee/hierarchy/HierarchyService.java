package org.remusrd.employee.hierarchy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.toUnmodifiableList;

public class HierarchyService {

    public Hierarchy retrieveEmployeeHierarchy(Map<String, String> employeeSupervisors) {
        final Map<String, List<String>> employeesBySupervisor = employeeSupervisors.entrySet()
                .stream()
                .collect(
                        groupingBy(
                                Map.Entry::getValue,
                                mapping(Map.Entry::getKey, toUnmodifiableList()))
                );

        Employee topSupervisor = null;


        final int employeesToBeProcessed = Math.toIntExact(
                employeesBySupervisor.entrySet()
                        .stream()
                        .map(this::addSupervisorAsEmployee)
                        .flatMap(Collection::stream)
                        .distinct()
                        .count());

        final HashMap<String, Employee> processedEmployees = new HashMap<>();

        while (processedEmployees.size() < employeesToBeProcessed) {
            for (final Map.Entry<String, List<String>> employee : employeesBySupervisor.entrySet()) {
                if (topSupervisor == null) {
                    topSupervisor = mapEmployee(employeesBySupervisor, processedEmployees, employee.getKey());
                } else if (employeesBySupervisor.get(employee.getKey()).contains(topSupervisor.getName())) {
                    topSupervisor = mapEmployee(employeesBySupervisor, processedEmployees, employee.getKey());
                }
            }
        }

        return new Hierarchy(topSupervisor);
    }

    private List<String> addSupervisorAsEmployee(Map.Entry<String, List<String>> supervisorEmployees) {
        final ArrayList<String> employees = new ArrayList<>(supervisorEmployees.getValue());
        employees.add(supervisorEmployees.getKey());
        return Collections.unmodifiableList(employees);
    }

    private Employee mapEmployee(Map<String, List<String>> employeeSupervisors, Map<String, Employee> processedEmployees, String employeeName) {
        if (processedEmployees.containsKey(employeeName)) {
            return processedEmployees.get(employeeName);
        }

        final List<String> subordinates = employeeSupervisors.get(employeeName) == null ? Collections.emptyList() : employeeSupervisors.get(employeeName);


        final Employee employee = subordinates
                .stream()
                .map(it -> mapEmployee(employeeSupervisors, processedEmployees, it))
                .collect(collectingAndThen(toUnmodifiableList(), listOfEmployees -> new Employee(employeeName, listOfEmployees)));

        processedEmployees.put(employeeName, employee);

        return employee;
    }
}
