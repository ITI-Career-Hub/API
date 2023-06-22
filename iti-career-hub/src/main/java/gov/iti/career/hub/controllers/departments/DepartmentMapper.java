package gov.iti.career.hub.controllers.departments;
import gov.iti.career.hub.persistence.entities.Department;
import gov.iti.career.hub.controllers.departments.dtos.requests.UpdateDepartmentRequest;
import gov.iti.career.hub.controllers.departments.dtos.responses.GetDepartmentResponse;
import gov.iti.career.hub.controllers.departments.dtos.responses.UpdateDepartmentResponse;

import gov.iti.career.hub.persistence.entities.Staff;
import gov.iti.career.hub.persistence.repositories.StaffRepository;
import org.mapstruct.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class DepartmentMapper{
private  StaffRepository StaffRepository;
    @Mapping(source = "manager.id", target = "managerId")
    @Mapping(source = "manager.firstName", target = "managerName")
    public abstract GetDepartmentResponse toGetDepartmentResponsedto(Department department);

    @Mapping(source = "manager.id", target = "managerId")
    @Mapping(source = "manager.firstName", target = "managerName")
    public abstract UpdateDepartmentResponse toupdateDepartmentResponsedto(Department department);
    @Mapping(source = "managerId", target = "manager.id")
    public abstract Department ToEntity(UpdateDepartmentRequest updateDepartmentRequest);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "manager.id", target = "managerId")
    @Mapping(source = "manager.firstName", target = "managerName")
    public abstract Collection<GetDepartmentResponse> toGetDepartmentsResponseDto(Collection<Department> departmens);
    @Named("fetchmanagerById")
    protected Staff fetchmanagerById(Integer id){
        return StaffRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "manger Not Found"));
    }
}