package gov.iti.career.hub.controllers.Departments;
import gov.iti.career.hub.persistence.entities.Department;
import gov.iti.career.hub.controllers.Departments.dtos.requests.UpdateDepartmentRequest;
import gov.iti.career.hub.controllers.Departments.dtos.responses.GetDepartmentResponse;
import gov.iti.career.hub.controllers.Departments.dtos.responses.UpdateDepartmentResponse;

import gov.iti.career.hub.persistence.entities.Staff;
import gov.iti.career.hub.persistence.repositories.StaffRepository;
import org.mapstruct.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class DepartmentMapper{
private  StaffRepository StaffRepository;

    public abstract GetDepartmentResponse toGetDepartmentResponsedto(Department department);


    public abstract UpdateDepartmentResponse toupdateDepartmentResponsedto(Department department);
    public abstract Department ToEntity(UpdateDepartmentRequest updateDepartmentRequest);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "mangerId", target = "manger.id")
    @Mapping(source = "mangerName", target = "manger.first_name")
    public abstract Collection<GetDepartmentResponse> toGetDepartmentsResponseDto(Collection<Department> departmens);
    @Named("fetchmangerById")
    protected Staff fetchmangerById(Integer id){
        return StaffRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "manger Not Found"));
    }
}