package gov.iti.career.hub.services;

import gov.iti.career.hub.controllers.register.RegistrationMapper;
import gov.iti.career.hub.controllers.register.ResourceCreatedMessage;
import gov.iti.career.hub.controllers.register.dtos.requests.RegisterStudentRequest;
import gov.iti.career.hub.persistence.entities.Student;
import gov.iti.career.hub.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final RegistrationMapper mapper;
    private final ObjectFactory<JsonWebSignature> signedRegistrationToken;
    private final ObjectFactory<JwtClaims> registrationClaims;
    private final EmailService emailService;
    public ResourceCreatedMessage registerStudent(RegisterStudentRequest request) throws JoseException {
        Student student = mapper.toEntity(request);
        userRepository.save(student);
        JsonWebSignature token = signedRegistrationToken.getObject();
        JwtClaims claims = registrationClaims.getObject();
        claims.setSubject(student.getId().toString());
        token.setPayload(claims.toJson());
        emailService.sendEmail(
                request.email(),
                "CAREER HUB REGISTRATION",
                token.getCompactSerialization()
        );

        return new ResourceCreatedMessage("Student Resource Created Successfully");
    }
}
