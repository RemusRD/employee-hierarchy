package org.remusrd.employee.hierarchy;

import java.util.List;
import java.util.Objects;

public class Employee {
    private final String name;
    private final List<Employee> subordinates;
    private final List<String> supervisorsName;

    public Employee(String name, List<Employee> subordinates, List<String> supervisorsName) {
        this.name = name;
        this.subordinates = subordinates;
        this.supervisorsName = supervisorsName;
    }

    public final List<Employee> getSubordinates() {
        return subordinates;
    }

    public final String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
                Objects.equals(subordinates, employee.subordinates) &&
                Objects.equals(supervisorsName, employee.supervisorsName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, subordinates, supervisorsName);
    }

    public List<String> getSupervisorsName() {
        return supervisorsName;
    }
}
