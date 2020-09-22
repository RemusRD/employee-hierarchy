package org.remusrd.employee.hierarchy;

public class Hierarchy {
    private final Employee supervisor;

    public Hierarchy(Employee supervisor) {
        this.supervisor = supervisor;
    }

    public Employee getSupervisor() {
        return supervisor;
    }
}
