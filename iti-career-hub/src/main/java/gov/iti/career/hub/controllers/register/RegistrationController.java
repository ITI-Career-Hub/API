package gov.iti.career.hub.controllers.register;

import gov.iti.career.hub.controllers.register.dtos.requests.RegisterStudentRequest;
import gov.iti.career.hub.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.jose4j.lang.JoseException;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResourceCreatedMessage> registerStudent(@RequestBody RegisterStudentRequest request) throws JoseException {
        return ResponseEntity.ok(registrationService.registerStudent(request));
    }
}
