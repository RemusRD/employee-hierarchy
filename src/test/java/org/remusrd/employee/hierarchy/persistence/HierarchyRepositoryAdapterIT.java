package org.remusrd.employee.hierarchy.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.remusrd.employee.hierarchy.Employee;
import org.remusrd.employee.hierarchy.EmployeeFactory;
import org.remusrd.employee.hierarchy.Hierarchy;
import org.remusrd.employee.hierarchy.persistence.dto.SupervisorRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(HierarchyRepositoryAdapter.class)
class HierarchyRepositoryAdapterIT {

    @Autowired
    private HierarchyRepositoryAdapter sut;

    @Test
    void shouldSave() {
        Hierarchy hierarchy = EmployeeFactory.simpleHierarchy();

        sut.save(hierarchy);

        final Hierarchy peteHierarchy = sut.findByName("Pete");

        final Employee supervisor = peteHierarchy.getSupervisor();
        assertThat(supervisor.getName()).isEqualTo("Jonas");
        final Employee sophie = supervisor.getSubordinates().get(0);
        assertThat(sophie.getName()).isEqualTo("Sophie");
        assertThat(sophie.getSubordinates().get(0).getName()).isEqualTo("Pete");

    }
}