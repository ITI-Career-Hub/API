package gov.iti.career.hub.persistence.repositories;

import gov.iti.career.hub.persistence.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer>,
                                            ListPagingAndSortingRepository<Student, Integer> {
    List<Student> findByDepartment_Id(@NonNull Integer id);

}
