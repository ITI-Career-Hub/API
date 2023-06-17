package gov.iti.career.hub.controllers.companies;


import gov.iti.career.hub.controllers.companies.dtos.requests.UpdateCompanyRequest;
import gov.iti.career.hub.controllers.companies.dtos.requests.UpdateCompanyResponse;
import gov.iti.career.hub.controllers.companies.dtos.responses.GetCompanyResponse;
import gov.iti.career.hub.services.CompanyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/company")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
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
}
