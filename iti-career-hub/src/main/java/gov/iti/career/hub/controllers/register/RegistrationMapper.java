package gov.iti.career.hub.controllers.register;

import gov.iti.career.hub.controllers.register.dtos.requests.RegisterStudentRequest;
import gov.iti.career.hub.persistence.entities.Student;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RegistrationMapper {
    @Mapping(source = "departmentId", target = "department.id")
    Student registerStudentRequestMappings(RegisterStudentRequest registerStudentRequest);

    @InheritConfiguration(name = "registerStudentRequestMappings")
    Student toEntity(RegisterStudentRequest registerStudentRequest);


}