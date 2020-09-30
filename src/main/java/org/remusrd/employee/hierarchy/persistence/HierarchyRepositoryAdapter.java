package org.remusrd.employee.hierarchy.persistence;

import org.remusrd.employee.hierarchy.Employee;
import org.remusrd.employee.hierarchy.Hierarchy;
import org.remusrd.employee.hierarchy.HierarchyRepository;
import org.remusrd.employee.hierarchy.NotFoundException;
import org.remusrd.employee.hierarchy.persistence.dto.SupervisorRelation;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class HierarchyRepositoryAdapter implements HierarchyRepository {

    private final HierarchyJpaRepository repository;

    public HierarchyRepositoryAdapter(HierarchyJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Hierarchy hierarchy) {
        var supervisor = hierarchy.getSupervisor();

        final List<SupervisorRelation> relationList = flattenEmployees(supervisor)
                .stream()
                .map(this::calculateRelations)
                .collect(Collectors.toUnmodifiableList());

        repository.saveAll(relationList);
    }

    @Override
    public SupervisorRelation findByName(String name) {
        return repository.findById(name).orElseThrow(NotFoundException::new);
    }

    private List<Employee> flattenEmployees(Employee employee) {
        final var listOfEmployees = new ArrayList<Employee>();
        listOfEmployees.add(employee);
        List<Employee> employeeList = employee.getSubordinates()
                .stream().map(this::flattenEmployees)
                .flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableList());

        listOfEmployees.addAll(employeeList);
        return listOfEmployees;
    }

    private SupervisorRelation calculateRelations(Employee employee) {
        final String firstSupervisorName = getValueOrDefault(employee.getSupervisorsName(), 0);
        final String secondSupervisorName = getValueOrDefault(employee.getSupervisorsName(), 1);

        return new SupervisorRelation(employee.getName(),firstSupervisorName,secondSupervisorName );
    }

    private String getValueOrDefault(List<String> values, int index){
        try {
            return values.get(index);
        } catch (Exception e){
            return "";
        }
    }
}
