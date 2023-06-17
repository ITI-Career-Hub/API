package gov.iti.career.hub.controllers.companies;


import gov.iti.career.hub.controllers.companies.dtos.requests.UpdateCompanyRequest;
import gov.iti.career.hub.controllers.companies.dtos.requests.UpdateCompanyResponse;
import gov.iti.career.hub.controllers.companies.dtos.responses.GetAllCompaniesResponse;
import gov.iti.career.hub.services.CompanyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/companies")
@AllArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    @GetMapping
    public ResponseEntity<Collection<GetAllCompaniesResponse>> findAllCompanies(){
        return ResponseEntity
                .ok(companyService.findAllCompanies());
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateCompanyResponse> updateCompany(@PathVariable Integer id, @Valid @RequestBody UpdateCompanyRequest request){
        return ResponseEntity.ok(companyService.updateCompany(id, request));
    }
}
