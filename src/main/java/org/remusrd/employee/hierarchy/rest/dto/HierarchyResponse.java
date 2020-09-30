package org.remusrd.employee.hierarchy.rest.dto;

public class HierarchyResponse {
    private final EmployeeResponse supervisor;

    public HierarchyResponse(EmployeeResponse supervisor) {
        this.supervisor = supervisor;
    }

    public EmployeeResponse getSupervisor() {
        return supervisor;
    }
}
