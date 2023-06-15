package gov.iti.career.hub.persistence.repositories;

import gov.iti.career.hub.persistence.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListPagingAndSortingRepository;

public interface UserRepository extends JpaRepository<User, Integer>,
                                            ListPagingAndSortingRepository<User, Integer> {

}
