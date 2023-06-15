package gov.iti.career.hub.persistence.repositories;

import gov.iti.career.hub.persistence.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface StaffRepository extends JpaRepository<Staff, Integer>
                                            , ListPagingAndSortingRepository<Staff, Integer> {
}
