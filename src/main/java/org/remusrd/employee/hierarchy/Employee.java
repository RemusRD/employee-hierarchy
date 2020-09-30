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
    //FIXME: deep equals some times does not work
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Employee employee = (Employee) o;
        return name.equals(employee.name) &&
                subordinates.equals(employee.subordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, subordinates);
    }

}
