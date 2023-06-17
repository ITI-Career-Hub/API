package gov.iti.career.hub.controllers.students;

import gov.iti.career.hub.controllers.students.dtos.requests.ActivateStudentRequest;
import gov.iti.career.hub.controllers.students.dtos.requests.UpdateStudentRequest;
import gov.iti.career.hub.controllers.students.dtos.responses.ActivateStudentResponse;
import gov.iti.career.hub.controllers.students.dtos.responses.GetStudentResponse;
import gov.iti.career.hub.controllers.students.dtos.responses.UpdateStudentResponse;
import gov.iti.career.hub.persistence.entities.Department;
import gov.iti.career.hub.persistence.entities.Student;
import gov.iti.career.hub.persistence.repositories.DepartmentRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class StudentMapper {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Mapping(source = "departmentDiscipline", target = "department.discipline")
    @Mapping(source = "departmentName", target = "department.departmentName")
    @Mapping(source = "departmentId", target = "department.id")
    @Mapping(source = "roleName", target = "role.roleName")
    @Mapping(source = "roleId", target = "role.id")
    protected abstract Student getStudentResponseMappings(GetStudentResponse getStudentResponse);

    @Mapping(source = "departmentDiscipline", target = "department.discipline")
    @Mapping(source = "departmentName", target = "department.departmentName")
    @Mapping(source = "departmentId", target = "department.id")
    @Mapping(source = "roleName", target = "role.roleName")
    @Mapping(source = "roleId", target = "role.id")
    protected abstract Student activateStudentResponseMappings(ActivateStudentResponse activateStudentResponse);

    @Mapping(source = "departmentId", target = "department", qualifiedByName = "fetchDepartmentById")
    protected abstract Student updateStudentRequestMappings(UpdateStudentRequest updateStudentRequest);

    @Mapping(source = "departmentName", target = "department.departmentName")
    @Mapping(source = "departmentId", target = "department.id")
    protected abstract Student updateStudentResponseMappings(UpdateStudentResponse updateStudentResponse);

    @InheritConfiguration(name = "getStudentResponseMappings")
    public abstract Student toEntity(GetStudentResponse getStudentResponse);

    @InheritConfiguration(name = "updateStudentRequestMappings")
    public abstract Student toEntity(UpdateStudentRequest updateStudentRequest);


    public abstract Student toEntity(ActivateStudentRequest activateStudentRequest);

    @InheritInverseConfiguration(name = "getStudentResponseMappings")
    public abstract Collection<GetStudentResponse> collectionToDto(Collection<Student> students);

    @InheritInverseConfiguration(name = "updateStudentResponseMappings")
    public abstract UpdateStudentResponse toUpdateStudentResponseDto(Student student);

    @InheritInverseConfiguration(name = "getStudentResponseMappings")
    public abstract GetStudentResponse toGetStudentResponseDto(Student student);

    @InheritInverseConfiguration(name = "activateStudentResponseMappings")
    public abstract ActivateStudentResponse toActivateStudentResponseDto(Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @InheritConfiguration(name = "updateStudentRequestMappings")
    public abstract Student partialUpdate(UpdateStudentRequest updateStudentRequest, @MappingTarget Student student);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Student partialUpdate(ActivateStudentRequest activateStudentRequest, @MappingTarget Student student);
    @Named("fetchDepartmentById")
    protected Department fetchDepartmentById(Integer id) {
        return departmentRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Department Not Found"));
    }
}
