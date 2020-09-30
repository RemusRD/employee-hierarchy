package org.remusrd.employee.hierarchy.persistence;

import org.remusrd.employee.hierarchy.persistence.dto.SupervisorRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HierarchyJpaRepository extends JpaRepository<SupervisorRelation, String> {

}
