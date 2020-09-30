package org.remusrd.employee.hierarchy.persistence;

import org.remusrd.employee.hierarchy.Hierarchy;
import org.remusrd.employee.hierarchy.HierarchyRepository;

public class HierarchyRepositoryAdapter implements HierarchyRepository {

    @Override
    public void save(Hierarchy hierarchy) {
        //TODO: make this faster

        //hierarchy.getSupervisor().getSupervisor();

    }
}
