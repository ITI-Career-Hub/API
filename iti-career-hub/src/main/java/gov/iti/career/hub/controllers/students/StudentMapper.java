package gov.iti.career.hub.controllers.students;

import gov.iti.career.hub.controllers.students.dtos.requests.UpdateStudentRequest;
import gov.iti.career.hub.controllers.students.dtos.responses.GetAllStudentsResponse;
import gov.iti.career.hub.controllers.students.dtos.responses.UpdateStudentResponse;
import gov.iti.career.hub.persistence.entities.Student;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Collection;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    @Mapping(source = "departmentDiscipline", target = "department.discipline")
    @Mapping(source = "departmentName", target = "department.departmentName")
    @Mapping(source = "departmentId", target = "department.id")
    @Mapping(source = "roleName", target = "role.roleName")
    @Mapping(source = "roleId", target = "role.id")
    Student getAllStudentsResponseMappings(GetAllStudentsResponse getAllStudentsResponse);

    @InheritConfiguration(name = "getAllStudentsResponseMappings")
    Student toEntity(GetAllStudentsResponse getAllStudentsResponse);
    Student toEntity(UpdateStudentRequest updateStudentRequest);
    @InheritInverseConfiguration(name = "getAllStudentsResponseMappings")
    Collection<GetAllStudentsResponse> collectionToDto(Collection<Student> students);
    UpdateStudentResponse toUpdateStudentResponseDto(Student student);
    @InheritInverseConfiguration(name = "getAllStudentsResponseMappings")
    GetAllStudentsResponse toGetAllStudentsResponseDto(Student student);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Student partialUpdate(UpdateStudentRequest updateStudentRequest, @MappingTarget Student student);
}
