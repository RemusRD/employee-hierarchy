package org.remusrd.employee.hierarchy;

import java.util.List;

public class Employee {
    public final String name;
    public final List<Employee> subordinates;

    public final List<Employee> getSubordinates() {
        return subordinates;
    }

    public Employee(String name, List<Employee> subordinates) {
        this.name = name;
        this.subordinates = subordinates;
    }

    public final String getName() {
        return name;
    }
}
