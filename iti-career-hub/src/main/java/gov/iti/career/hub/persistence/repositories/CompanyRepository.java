package gov.iti.career.hub.persistence.repositories;

import gov.iti.career.hub.persistence.entities.Company;
import gov.iti.career.hub.persistence.entities.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer>
                                            , ListPagingAndSortingRepository<Company, Integer> {
    Optional<Company> findByUsername(String username);
}
