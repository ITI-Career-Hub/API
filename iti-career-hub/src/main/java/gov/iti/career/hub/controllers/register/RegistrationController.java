package gov.iti.career.hub.controllers.register;

import gov.iti.career.hub.controllers.register.dtos.requests.RegisterCompanyRequest;
import gov.iti.career.hub.controllers.register.dtos.requests.RegisterStaffRequest;
import gov.iti.career.hub.controllers.register.dtos.requests.RegisterStudentRequest;
import gov.iti.career.hub.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.jose4j.lang.JoseException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/student")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResourceCreatedMessage> registerStudent(@RequestBody RegisterStudentRequest request) throws JoseException {
        return ResponseEntity.ok(registrationService.registerStudent(request));
    }

    @PostMapping("/company")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResourceCreatedMessage> registerCompany(@RequestBody RegisterCompanyRequest request) throws JoseException {
        return ResponseEntity.ok(registrationService.registerCompany(request));
    }

    @PostMapping("/staff")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResourceCreatedMessage> registerStaff(@RequestBody RegisterStaffRequest request) throws JoseException {
        return ResponseEntity.ok(registrationService.registerStaff(request));
    }
}
