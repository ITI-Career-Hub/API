package gov.iti.career.hub.persistence.repositories;

import gov.iti.career.hub.persistence.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface StudentRepository extends JpaRepository<Student, Integer>,
                                            ListPagingAndSortingRepository<Student, Integer> {
}
