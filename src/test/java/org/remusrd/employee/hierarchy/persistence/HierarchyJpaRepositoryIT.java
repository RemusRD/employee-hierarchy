package org.remusrd.employee.hierarchy.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.remusrd.employee.hierarchy.persistence.dto.SupervisorRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class HierarchyJpaRepositoryIT {

    @Autowired
    private HierarchyJpaRepository hierarchyJpaRepository;

    @Test
    void shouldSaveCorrectly() {
        final SupervisorRelation supervisorRelation = new SupervisorRelation("Pete", "Nick", "Sophie");

        hierarchyJpaRepository.save(supervisorRelation);

        final var result = hierarchyJpaRepository.findById("Pete").get();

        assertThat(result.getEmployeeName()).isEqualTo("Pete");
        assertThat(result.getFirstSupervisorName()).isEqualTo("Nick");
        assertThat(result.getSecondSupervisorName()).isEqualTo("Sophie");


    }
}
