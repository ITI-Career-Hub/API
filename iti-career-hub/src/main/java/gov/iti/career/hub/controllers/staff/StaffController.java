package gov.iti.career.hub.controllers.staff;

import gov.iti.career.hub.controllers.companies.dtos.requests.ActivateCompanyRequest;
import gov.iti.career.hub.controllers.companies.dtos.responses.ActivateCompanyResponse;
import gov.iti.career.hub.controllers.staff.dtos.requests.ActivateStaffRequest;
import gov.iti.career.hub.controllers.staff.dtos.requests.UpdateStaffRequest;
import gov.iti.career.hub.controllers.staff.dtos.responses.ActivateStaffResponse;
import gov.iti.career.hub.controllers.staff.dtos.responses.GetStaffResponse;
import gov.iti.career.hub.controllers.staff.dtos.responses.UpdateStaffResponse;
import gov.iti.career.hub.services.StaffService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/staff")
@AllArgsConstructor
public class StaffController {

    private final StaffService staffService;
    @GetMapping
    public ResponseEntity<Collection<GetStaffResponse>> findAllStaff(){
        return ResponseEntity
                .ok(staffService.findAllStaff());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetStaffResponse> findAllStaff(@PathVariable Integer id){
        return ResponseEntity
                .ok(staffService.findStaffById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateStaffResponse> updateStaff(@PathVariable Integer id, @Valid @RequestBody UpdateStaffRequest request){
        return ResponseEntity.ok(staffService.updateStaff(id, request));
    }

    @PostMapping("/register")
    public ResponseEntity<ActivateStaffResponse> registerStudent(@RequestParam("token") String token,
                                                                 @RequestBody ActivateStaffRequest request) throws InvalidJwtException, MalformedClaimException {
        return ResponseEntity.ok(staffService.activateStaff(token, request));
    }
}
