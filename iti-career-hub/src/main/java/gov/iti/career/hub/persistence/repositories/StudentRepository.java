package gov.iti.career.hub.persistence.repositories;

import gov.iti.career.hub.persistence.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer>,
                                            ListPagingAndSortingRepository<Student, Integer> {

    @Query("""
            SELECT s FROM Student s
            WHERE s.isActive = true
            AND s.department.id = :id
        """)
    Collection<Student> findAllActiveStudentsByDepartmentId(Integer id);

    Optional<Student> findByUsername(String username);

    List<Student> findAllByDepartmentId(Integer departmentId);
}
