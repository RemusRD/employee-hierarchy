package org.remusrd.employee.hierarchy.persistence.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SupervisorRelation {
    @Id
    @Column(name = "employee_name")
    private String employeeName;
    @Column(name = "first_supervisor_name")
    private String firstSupervisorName;
    @Column(name = "second_supervisor_name")
    private String secondSupervisorName;

    public SupervisorRelation(String employeeName, String firstSupervisorName, String secondSupervisorName) {
        this.employeeName = employeeName;
        this.firstSupervisorName = firstSupervisorName;
        this.secondSupervisorName = secondSupervisorName;
    }

    public SupervisorRelation() {

    }

    public String getEmployeeName() {
        return employeeName;
    }

    public String getFirstSupervisorName() {
        return firstSupervisorName;
    }

    public String getSecondSupervisorName() {
        return secondSupervisorName;
    }
}
