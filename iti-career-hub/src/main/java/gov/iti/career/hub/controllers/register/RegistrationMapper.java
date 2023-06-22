package gov.iti.career.hub.controllers.register;

import gov.iti.career.hub.controllers.register.dtos.requests.RegisterStaffRequest;
import gov.iti.career.hub.controllers.register.dtos.requests.RegisterCompanyRequest;
import gov.iti.career.hub.controllers.register.dtos.requests.RegisterStudentRequest;
import gov.iti.career.hub.persistence.entities.Company;
import gov.iti.career.hub.persistence.entities.Staff;
import gov.iti.career.hub.persistence.entities.Student;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RegistrationMapper {
    @Mapping(source = "departmentId", target = "department.id")
    Student registerStudentRequestMappings(RegisterStudentRequest registerStudentRequest);

    @InheritConfiguration(name = "registerStudentRequestMappings")
    Student toStudentEntity(RegisterStudentRequest registerStudentRequest);

    Company toCompanyEntity(RegisterCompanyRequest registerCompanyRequest);

    @Mapping(source = "departmentId", target = "department.id")
    Staff toStaffEntity(RegisterStaffRequest registerStaffRequest);

    @Mapping(source = "department.id", target = "departmentId")
    public RegisterStudentRequest toRegisterStudentRequest(Student student);
}