package org.remusrd.employee.hierarchy;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Employee {
    private final String name;
    private final List<Employee> subordinates;

    public Employee(String name, List<Employee> subordinates) {
        this.name = name;
        this.subordinates = subordinates;
    }

    public final List<Employee> getSubordinates() {
        return subordinates;
    }

    public final String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, subordinates);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) &&
        new HashSet<>(subordinates).equals(new HashSet<>(employee.subordinates));
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Employee.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("subordinates=" + subordinates)
                .toString();
    }

}
