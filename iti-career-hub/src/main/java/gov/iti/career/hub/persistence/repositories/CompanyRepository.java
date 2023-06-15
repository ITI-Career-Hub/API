package gov.iti.career.hub.persistence.repositories;

import gov.iti.career.hub.persistence.entities.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer>
                                            , ListPagingAndSortingRepository<Company, Integer> {
}
