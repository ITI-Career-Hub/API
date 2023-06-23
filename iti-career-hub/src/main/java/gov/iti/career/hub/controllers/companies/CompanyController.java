package gov.iti.career.hub.controllers.companies;


import gov.iti.career.hub.controllers.companies.dtos.requests.ActivateCompanyRequest;
import gov.iti.career.hub.controllers.companies.dtos.responses.ActivateCompanyResponse;
import gov.iti.career.hub.controllers.companies.dtos.requests.UpdateCompanyRequest;
import gov.iti.career.hub.controllers.companies.dtos.requests.UpdateCompanyResponse;
import gov.iti.career.hub.controllers.companies.dtos.responses.GetAllAppointmentsByCompanyAndEvent;
import gov.iti.career.hub.controllers.companies.dtos.responses.GetCompanyResponse;
import gov.iti.career.hub.controllers.register.dtos.requests.RegisterCompanyRequest;
import gov.iti.career.hub.controllers.register.dtos.requests.RegisterStudentRequest;
import gov.iti.career.hub.services.AppointmentService;
import gov.iti.career.hub.services.CompanyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<Collection<GetCompanyResponse>> findAllCompanies(){
        return ResponseEntity
                .ok(companyService.findAllCompanies());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetCompanyResponse> findCompanyById(@PathVariable Integer id){
        return ResponseEntity
                .ok(companyService.findCompanyById(id));
    }
    @PutMapping("{id}")
    public ResponseEntity<UpdateCompanyResponse> updateCompany(@PathVariable Integer id, @Valid @RequestBody UpdateCompanyRequest request){
        return ResponseEntity.ok(companyService.updateCompany(id, request));
    }

    @PostMapping("/register")
    public ResponseEntity<ActivateCompanyResponse> registerStudent(@RequestParam("token") String token,
                                                                   @RequestBody ActivateCompanyRequest request) throws InvalidJwtException, MalformedClaimException {
        return ResponseEntity.ok(companyService.activateCompany(token, request));
    }

    @PostMapping("/validate")
    public ResponseEntity<RegisterCompanyRequest> registerStudentData(@RequestParam("token") String token) throws InvalidJwtException, MalformedClaimException {
        return ResponseEntity.ok(companyService.registerCompanyData(token));
    }

    @GetMapping("{companyId}/event/{eventId}/appointment")
    public ResponseEntity<Collection<GetAllAppointmentsByCompanyAndEvent>> getAllAppointmentsByCompanyAndEvent(@PathVariable Integer companyId,
                                                                                                   @PathVariable Integer eventId ){
        return ResponseEntity.ok(appointmentService.getAllAppointmentsByCompanyAndEvent(companyId, eventId));
    }
}
