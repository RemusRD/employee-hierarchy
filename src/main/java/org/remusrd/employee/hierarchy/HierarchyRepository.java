package org.remusrd.employee.hierarchy;

import org.remusrd.employee.hierarchy.persistence.dto.SupervisorRelation;

public interface HierarchyRepository {

    void save(Hierarchy hierarchy);

    Hierarchy findByName(String name);
}
