package org.remusrd.employee.hierarchy.rest;

import org.remusrd.employee.hierarchy.Employee;
import org.remusrd.employee.hierarchy.Hierarchy;

import java.util.Collections;
import java.util.List;

public class EmployeeFactory {

    public static Employee simpleEmployee() {
        return new Employee("Jonas", List.of(
                new Employee("Sophie",
                        List.of(new Employee("Pete", Collections.emptyList()), new Employee("Barbara", Collections.emptyList())))));
    }

    public static Hierarchy simpleHierarchy() {
        return new Hierarchy(simpleEmployee());
    }
}
