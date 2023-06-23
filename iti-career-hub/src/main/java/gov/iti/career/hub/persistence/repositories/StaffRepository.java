package gov.iti.career.hub.persistence.repositories;

import gov.iti.career.hub.persistence.entities.Staff;
import gov.iti.career.hub.persistence.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Integer>
                                            , ListPagingAndSortingRepository<Staff, Integer> {
    Optional<Staff> findByUsername(String username);
}
