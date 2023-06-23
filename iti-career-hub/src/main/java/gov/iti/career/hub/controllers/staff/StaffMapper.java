package gov.iti.career.hub.controllers.staff;

import gov.iti.career.hub.controllers.staff.dtos.requests.ActivateStaffRequest;
import gov.iti.career.hub.controllers.staff.dtos.requests.UpdateStaffRequest;
import gov.iti.career.hub.controllers.staff.dtos.responses.ActivateStaffResponse;
import gov.iti.career.hub.controllers.staff.dtos.responses.GetStaffResponse;
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
    protected abstract Staff getStaffResponseMappings(GetStaffResponse getStaffResponse);

    @Mapping(source = "departmentId", target = "department", qualifiedByName = "fetchDepartmentById")
    @Mapping(source = "roleId", target = "role", qualifiedByName = "fetchRoleById")
    protected abstract Staff updateStaffRequestMappings(UpdateStaffRequest updateStaffRequest);

    @Mapping(source = "departmentName", target = "department.departmentName")
    @Mapping(source = "departmentId", target = "department.id")
    @Mapping(source = "roleName", target = "role.roleName")
    @Mapping(source = "roleId", target = "role.id")
    protected abstract Staff updateStaffResponseMappings(UpdateStaffResponse updateStaffResponse);

    @Mapping(source = "departmentName", target = "department.departmentName")
    @Mapping(source = "departmentId", target = "department.id")
    @Mapping(source = "roleRoleName", target = "role.roleName")
    @Mapping(source = "roleId", target = "role.id")
    protected abstract Staff activateStaffResponseMappings(ActivateStaffResponse activateStaffResponse);

    // TO ENTITIES

    @InheritConfiguration(name = "updateStaffRequestMappings")
    public abstract Staff toEntity(UpdateStaffRequest updateStaffRequest);


    // TO DTO
    @InheritInverseConfiguration(name = "getStaffResponseMappings")
    public abstract GetStaffResponse toGetStaffResponseDto(Staff staff);

    @InheritInverseConfiguration(name = "getStaffResponseMappings")
    public abstract Collection<GetStaffResponse> collectionToDto(Collection<Staff> staff);

    @InheritInverseConfiguration(name = "updateStaffResponseMappings")
    public abstract UpdateStaffResponse toUpdateStaffResponseDto(Staff staff);

    //UPDATE
    @InheritConfiguration(name = "updateStaffRequestMappings")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Staff partialUpdate(UpdateStaffRequest updateStaffRequest, @MappingTarget Staff staff);

    @Named("fetchRoleById")
    protected Role fetchRoleById(Integer id) {
        return roleRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Role Not Found"));
    }

    @Named("fetchDepartmentById")
    protected Department fetchDepartmentById(Integer id) {
        return departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Department Not Found"));
    }

    abstract Staff toEntity(ActivateStaffRequest activateStaffRequest);

    abstract ActivateStaffRequest toActivateStaffRequestDto(Staff staff);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Staff partialUpdate(ActivateStaffRequest activateStaffRequest, @MappingTarget Staff staff);

    @Mapping(source = "departmentDepartmentName", target = "department.departmentName")
    @Mapping(source = "departmentId", target = "department.id")
    @Mapping(source = "roleRoleName", target = "role.roleName")
    @Mapping(source = "roleId", target = "role.id")
    abstract Staff toEntity(ActivateStaffResponse activateStaffResponse);

    @InheritInverseConfiguration(name = "activateStaffResponseMappings")
    public abstract ActivateStaffResponse toActivateStaffResponseDto(Staff staff);

    @InheritConfiguration(name = "activateStaffResponseMappings")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Staff partialUpdate(ActivateStaffResponse activateStaffResponse, @MappingTarget Staff staff);
}