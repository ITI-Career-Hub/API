package gov.iti.career.hub.persistence.repositories;

import gov.iti.career.hub.persistence.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer>
                                            , ListPagingAndSortingRepository<Role, Integer> {

    Role findByRoleName(String roleName);
}
