package gov.iti.career.hub.persistence.repositories;

import gov.iti.career.hub.persistence.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListPagingAndSortingRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Set;

public interface RoomRepository extends JpaRepository<Room, Integer>
        , ListPagingAndSortingRepository<Room, Integer> {}
