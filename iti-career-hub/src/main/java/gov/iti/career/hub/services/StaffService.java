package gov.iti.career.hub.services;

import gov.iti.career.hub.controllers.staff.StaffMapper;
import gov.iti.career.hub.controllers.staff.dtos.requests.UpdateStaffRequest;
import gov.iti.career.hub.controllers.staff.dtos.responses.GetAllStaffResponse;
import gov.iti.career.hub.controllers.staff.dtos.responses.UpdateStaffResponse;
import gov.iti.career.hub.persistence.entities.Role;
import gov.iti.career.hub.persistence.entities.Staff;
import gov.iti.career.hub.persistence.entities.Student;
import gov.iti.career.hub.persistence.repositories.StaffRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

import static io.swagger.v3.core.util.Yaml.mapper;

@Service
@AllArgsConstructor
public class StaffService {

    private final StaffMapper staffMapper;
    private final StaffRepository staffRepository;

    public Collection<GetAllStaffResponse> findAllStaff() {
        return staffMapper.collectionToDto(staffRepository.findAll());
    }


    @Transactional
    public UpdateStaffResponse updateStaff(Integer id, UpdateStaffRequest request) {
        Staff staff = staffRepository.findById(id)
            .orElseThrow( () ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")
            );
        staffMapper.partialUpdate(request, staff);
        return staffMapper.toUpdateStaffResponseDto(staffRepository.save(staff));
    }
}
