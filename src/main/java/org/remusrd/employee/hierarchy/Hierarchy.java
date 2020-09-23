package org.remusrd.employee.hierarchy;

import java.util.StringJoiner;

public class Hierarchy {
    private final Employee supervisor;

    public Hierarchy(Employee supervisor) {
        this.supervisor = supervisor;
    }

    public Employee getSupervisor() {
        return supervisor;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Hierarchy.class.getSimpleName() + "[", "]")
                .add("supervisor=" + supervisor)
                .toString();
    }
}
