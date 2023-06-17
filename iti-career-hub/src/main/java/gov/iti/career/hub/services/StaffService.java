package gov.iti.career.hub.services;

import gov.iti.career.hub.controllers.staff.StaffMapper;
import gov.iti.career.hub.controllers.staff.dtos.requests.UpdateStaffRequest;
import gov.iti.career.hub.controllers.staff.dtos.responses.GetStaffResponse;
import gov.iti.career.hub.controllers.staff.dtos.responses.UpdateStaffResponse;
import gov.iti.career.hub.persistence.entities.Staff;
import gov.iti.career.hub.persistence.repositories.StaffRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Service
@AllArgsConstructor
public class StaffService {

    private final StaffMapper staffMapper;
    private final StaffRepository staffRepository;

    public Collection<GetStaffResponse> findAllStaff() {
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

    public GetStaffResponse findStaffById(Integer id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")
                );
        return staffMapper.toGetStaffResponseDto(staff);
    }
}
