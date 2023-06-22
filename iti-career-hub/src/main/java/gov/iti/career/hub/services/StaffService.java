package gov.iti.career.hub.services;

import gov.iti.career.hub.controllers.companies.dtos.requests.ActivateCompanyRequest;
import gov.iti.career.hub.controllers.companies.dtos.responses.ActivateCompanyResponse;
import gov.iti.career.hub.controllers.register.RegistrationMapper;
import gov.iti.career.hub.controllers.register.dtos.requests.RegisterStaffRequest;
import gov.iti.career.hub.controllers.register.dtos.requests.RegisterStudentRequest;
import gov.iti.career.hub.controllers.staff.StaffMapper;
import gov.iti.career.hub.controllers.staff.dtos.requests.ActivateStaffRequest;
import gov.iti.career.hub.controllers.staff.dtos.requests.UpdateStaffRequest;
import gov.iti.career.hub.controllers.staff.dtos.responses.ActivateStaffResponse;
import gov.iti.career.hub.controllers.staff.dtos.responses.GetStaffResponse;
import gov.iti.career.hub.controllers.staff.dtos.responses.UpdateStaffResponse;
import gov.iti.career.hub.persistence.entities.Company;
import gov.iti.career.hub.persistence.entities.Staff;
import gov.iti.career.hub.persistence.entities.Student;
import gov.iti.career.hub.persistence.repositories.RoleRepository;
import gov.iti.career.hub.persistence.repositories.StaffRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Service
@AllArgsConstructor
public class StaffService {

    private final StaffMapper staffMapper;
    private final StaffRepository staffRepository;
    private final JwtConsumer jwtConsumer;
    private final RegistrationMapper mapper;
    private final RoleRepository roleRepository;

    public Collection<GetStaffResponse> findAllStaff() {
        return staffMapper.collectionToDto(staffRepository.findAll());
    }


    @Transactional
    public UpdateStaffResponse updateStaff(Integer id, UpdateStaffRequest request) {
        Staff staff = staffRepository.findById(id)
            .orElseThrow( () ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff Not Found")
            );
        staffMapper.partialUpdate(request, staff);
        return staffMapper.toUpdateStaffResponseDto(staffRepository.save(staff));
    }

    public GetStaffResponse findStaffById(Integer id) {
        Staff staff = staffRepository.findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff Not Found")
                );
        return staffMapper.toGetStaffResponseDto(staff);
    }

    public ActivateStaffResponse activateStaff(String token, ActivateStaffRequest request)
            throws InvalidJwtException, MalformedClaimException {

        JwtClaims claims = jwtConsumer.processToClaims(token);
        Integer staffId = Integer.parseInt(claims.getSubject());
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff Not Found")
                );
        if(!staff.getIsActive()){
            staffMapper.partialUpdate(request, staff);
            staff.setIsActive(true);
            return staffMapper.toActivateStaffResponseDto(staffRepository.save(staff));
        }
        else throw new RuntimeException("Token Already Consumed Exception");

    }

    public RegisterStaffRequest registerStaffData(String token) throws InvalidJwtException, MalformedClaimException {
        JwtClaims claims = jwtConsumer.processToClaims(token);
        Integer staffId = Integer.parseInt(claims.getSubject());
        Staff staff = staffRepository.findById(staffId).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff Not Found")
        );
        if(staff.getIsActive()){
            throw new RuntimeException("Token Already Consumed Exception");
        }
        return mapper.toRegisterStaffRequest(staff);
    }
}
