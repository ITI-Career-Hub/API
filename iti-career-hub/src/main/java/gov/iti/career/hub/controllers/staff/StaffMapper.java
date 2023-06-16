package gov.iti.career.hub.controllers.staff;

import gov.iti.career.hub.controllers.staff.dtos.requests.UpdateStaffRequest;
import gov.iti.career.hub.controllers.staff.dtos.responses.GetAllStaffResponse;
import gov.iti.career.hub.controllers.staff.dtos.responses.UpdateStaffResponse;
import gov.iti.career.hub.persistence.entities.Department;
import gov.iti.career.hub.persistence.entities.Role;
import gov.iti.career.hub.persistence.entities.Staff;
import gov.iti.career.hub.persistence.repositories.DepartmentRepository;
import gov.iti.career.hub.persistence.repositories.RoleRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class StaffMapper {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    // MAPPINGS
    @Mapping(source = "departmentDiscipline", target = "department.discipline")
    @Mapping(source = "departmentName", target = "department.departmentName")
    @Mapping(source = "departmentId", target = "department.id")
    @Mapping(source = "roleName", target = "role.roleName")
    @Mapping(source = "roleId", target = "role.id")
    protected abstract Staff getAllStaffResponseMappings(GetAllStaffResponse getAllStaffResponse);

    @Mapping(source = "departmentId", target = "department", qualifiedByName = "fetchDepartmentById")
    @Mapping(source = "roleId", target = "role", qualifiedByName = "fetchRoleById")
    protected abstract Staff updateStaffRequestMappings(UpdateStaffRequest updateStaffRequest);

    @Mapping(source = "departmentName", target = "department.departmentName")
    @Mapping(source = "departmentId", target = "department.id")
    @Mapping(source = "roleName", target = "role.roleName")
    @Mapping(source = "roleId", target = "role.id")
    protected abstract Staff updateStaffResponseMappings(UpdateStaffResponse updateStaffResponse);

    // TO ENTITIES
    @InheritConfiguration(name = "getAllStaffResponseMappings")
    public abstract Staff toEntity(GetAllStaffResponse getAllStudentsResponse);

    @InheritConfiguration(name = "updateStaffRequestMappings")
    public abstract Staff toEntity(UpdateStaffRequest updateStaffRequest);

    @InheritConfiguration(name = "updateStaffResponseMappings")
    public abstract Staff toEntity(UpdateStaffResponse updateStaffResponse);

    // TO DTO
    @InheritInverseConfiguration(name = "toEntity")
    public abstract GetAllStaffResponse toGetAllStaffResponseDto(Staff staff);

    @InheritInverseConfiguration(name = "toEntity")
    public abstract Collection<GetAllStaffResponse> collectionToDto(Collection<Staff> staff);
    @InheritInverseConfiguration(name = "updateStaffResponseMappings")
    public abstract UpdateStaffResponse toUpdateStaffResponseDto(Staff staff);

    //UPDATE
    @InheritConfiguration(name = "updateStaffRequestMappings")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Staff partialUpdate(UpdateStaffRequest updateStaffRequest, @MappingTarget Staff staff);

    @Named("fetchRoleById")
    protected Role fetchRoleById(Integer id){
        return roleRepository.findById(id)
            .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Role Not Found"));
    }

    @Named("fetchDepartmentById")
    protected Department fetchDepartmentById(Integer id){
        return departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Department Not Found"));
    }
}