package org.remusrd.employee.hierarchy;

import org.remusrd.employee.hierarchy.persistence.dto.SupervisorRelation;
import org.springframework.stereotype.Component;

@Component
public class HierarchyQueryService {
    private final HierarchyRepository repository;

    public HierarchyQueryService(HierarchyRepository repository) {
        this.repository = repository;
    }

    public Hierarchy getEmployeeSupervisors(String name){
        return repository.findByName(name);
    }
}
