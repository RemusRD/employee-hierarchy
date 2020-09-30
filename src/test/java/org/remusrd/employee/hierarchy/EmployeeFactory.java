package org.remusrd.employee.hierarchy;

import org.remusrd.employee.hierarchy.Employee;
import org.remusrd.employee.hierarchy.Hierarchy;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

public class EmployeeFactory {

    public static Employee simpleEmployee() {
        return new Employee("Jonas", List.of(
                new Employee("Sophie",
                        List.of(new Employee("Pete", emptyList(), List.of("Sophie", "Jonas")), new Employee("Barbara", emptyList(),  List.of("Sophie", "Jonas"))),  List.of("Jonas"))),
                        emptyList());
    }

    public static Hierarchy simpleHierarchy() {
        return new Hierarchy(simpleEmployee());
    }
}
