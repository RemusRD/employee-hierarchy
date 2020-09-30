package org.remusrd.employee.hierarchy.rest.dto;

import java.util.List;
import java.util.Objects;

public class EmployeeResponse {
    private final String name;
    private final List<EmployeeResponse> subordinates;

    public EmployeeResponse(String name, List<EmployeeResponse> subordinates) {
        this.name = name;
        this.subordinates = subordinates;
    }

    public final String getName() {
        return name;
    }

    public List<EmployeeResponse> getSubordinates() {
        return subordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final EmployeeResponse that = (EmployeeResponse) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(subordinates, that.subordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, subordinates);
    }
}
