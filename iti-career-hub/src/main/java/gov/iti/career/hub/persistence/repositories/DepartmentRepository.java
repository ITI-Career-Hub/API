package gov.iti.career.hub.persistence.repositories;

import gov.iti.career.hub.persistence.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer>
                                                , ListPagingAndSortingRepository<Department, Integer> {
}
