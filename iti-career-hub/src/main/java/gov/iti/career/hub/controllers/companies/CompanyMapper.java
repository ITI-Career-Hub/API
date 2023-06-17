package gov.iti.career.hub.controllers.companies;

import gov.iti.career.hub.controllers.companies.dtos.requests.UpdateCompanyRequest;
import gov.iti.career.hub.controllers.companies.dtos.requests.UpdateCompanyResponse;
import gov.iti.career.hub.controllers.companies.dtos.responses.GetAllCompaniesResponse;
import gov.iti.career.hub.persistence.entities.Company;
import org.mapstruct.*;

import java.util.Collection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class CompanyMapper {

    @Mapping(source = "roleName", target = "role.roleName")
    @Mapping(source = "roleId", target = "role.id")
    protected abstract Company getAllCompaniesResponseMappings(GetAllCompaniesResponse getAllCompaniesResponse);

    @InheritConfiguration(name = "getAllCompaniesResponseMappings")
    public abstract Company toEntity(GetAllCompaniesResponse getAllCompaniesResponse);

    public abstract Collection<GetAllCompaniesResponse> collectionToDto(Collection<Company> companies);

    @InheritInverseConfiguration(name = "getAllCompaniesResponseMappings")
    public abstract GetAllCompaniesResponse toGetAllCompaniesResponseDto(Company company);


    public abstract Company toEntity(UpdateCompanyRequest updateCompanyRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Company partialUpdate(UpdateCompanyRequest updateCompanyRequest, @MappingTarget Company company);

    public abstract UpdateCompanyResponse toUpdateCompanyResponseDto(Company company);
}
