package gov.iti.career.hub.services;

import gov.iti.career.hub.controllers.companies.dtos.requests.ActivateCompanyRequest;
import gov.iti.career.hub.controllers.companies.dtos.responses.ActivateCompanyResponse;
import gov.iti.career.hub.controllers.companies.CompanyMapper;
import gov.iti.career.hub.controllers.companies.dtos.requests.UpdateCompanyRequest;
import gov.iti.career.hub.controllers.companies.dtos.requests.UpdateCompanyResponse;
import gov.iti.career.hub.controllers.companies.dtos.responses.GetCompanyResponse;
import gov.iti.career.hub.persistence.entities.Company;
import gov.iti.career.hub.persistence.repositories.CompanyRepository;
import gov.iti.career.hub.persistence.repositories.RoleRepository;
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
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final RoleRepository roleRepository;
    private final CompanyMapper companyMapper;
    private final JwtConsumer jwtConsumer;

    public Collection<GetCompanyResponse> findAllCompanies() {
        return companyMapper.collectionToDto(companyRepository.findAll());
    }

    public UpdateCompanyResponse updateCompany(Integer id, UpdateCompanyRequest request) {
        Company company = companyRepository.findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Company Not Found")
                );
        companyMapper.partialUpdate(request, company);
        return companyMapper.toUpdateCompanyResponseDto(companyRepository.save(company));
    }

    public GetCompanyResponse findCompanyById(Integer id) {
        Company company = companyRepository.findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Company Not Found")
                );
        return companyMapper.toGetCompanyResponseDto(company);
    }

    public ActivateCompanyResponse activateCompany(String token, ActivateCompanyRequest request)
            throws InvalidJwtException, MalformedClaimException {

        JwtClaims claims = jwtConsumer.processToClaims(token);
        Integer companyId = Integer.parseInt(claims.getSubject());
        Company company = companyRepository.findById(companyId)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Company Not Found")
                );
        company.setRole(roleRepository.findByRoleName("COMPANY"));
        if(!company.getIsActive()){
            companyMapper.partialUpdate(request, company);
            company.setIsActive(true);
            return companyMapper.toActivateCompanyResponseDto(companyRepository.save(company));
        }
        else throw new RuntimeException("Token Already Consumed Exception");

    }
}
