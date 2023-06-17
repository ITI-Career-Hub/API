package gov.iti.career.hub.services;

import gov.iti.career.hub.controllers.companies.CompanyMapper;
import gov.iti.career.hub.controllers.companies.dtos.requests.UpdateCompanyRequest;
import gov.iti.career.hub.controllers.companies.dtos.requests.UpdateCompanyResponse;
import gov.iti.career.hub.controllers.companies.dtos.responses.GetAllCompaniesResponse;
import gov.iti.career.hub.persistence.entities.Company;
import gov.iti.career.hub.persistence.entities.Staff;
import gov.iti.career.hub.persistence.repositories.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Service
@AllArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public Collection<GetAllCompaniesResponse> findAllCompanies() {
        return companyMapper.collectionToDto(companyRepository.findAll());
    }

    public UpdateCompanyResponse updateCompany(Integer id, UpdateCompanyRequest request) {
        Company company = companyRepository.findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")
                );
        companyMapper.partialUpdate(request, company);
        return companyMapper.toUpdateCompanyResponseDto(companyRepository.save(company));
    }
}
