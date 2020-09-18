package org.remusrd.employee.hierarchy;

import java.util.List;

public class Hierarchy {
    private final List<Employee> employees;

    public Hierarchy(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee getSupervisor() {
        return employees.get(0);
    }
}
