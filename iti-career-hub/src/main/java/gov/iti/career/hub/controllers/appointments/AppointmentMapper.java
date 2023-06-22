package gov.iti.career.hub.controllers.appointments;

import java.util.Collection;

import gov.iti.career.hub.controllers.appointments.dtos.responses.GetAttendanceResponse;
import gov.iti.career.hub.persistence.entities.Attendance;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import gov.iti.career.hub.controllers.appointments.dtos.requests.AddAppointmentRequest;
import gov.iti.career.hub.controllers.appointments.dtos.requests.UpdateAppointmentRequest;
import gov.iti.career.hub.controllers.appointments.dtos.responses.AddAppointmentResponse;
import gov.iti.career.hub.controllers.appointments.dtos.responses.GetAppointmentResponse;
import gov.iti.career.hub.controllers.appointments.dtos.responses.UpdateAppointmentResponse;
import gov.iti.career.hub.persistence.entities.Appointment;
import gov.iti.career.hub.persistence.repositories.CompanyRepository;
import gov.iti.career.hub.persistence.repositories.DepartmentRepository;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppointmentMapper {

    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "department.departmentName", target = "departmentName")
    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "company.companyName", target = "companyName")
    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "event.eventName", target = "eventName")
    GetAppointmentResponse toGetAllAppointmentsResponseDto(Appointment appointment);

    @InheritConfiguration(name = "toGetAllAppointmentsResponseDto")
    Collection<GetAppointmentResponse> toGetAllAppointmentsResponseDto(Collection<Appointment> appointment);


    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "department.departmentName", target = "departmentName")
    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "company.companyName", target = "companyName")
    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "event.eventName", target = "eventName")
    @Mapping(source = "appointment_name", target = "appointmentName")
    AddAppointmentResponse toAddAppointmentResponseDto(Appointment appointment);


    @Mapping(source = "departmentId", target = "department.id")
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "eventId", target = "event.id")
    @Mapping(source = "appointmentName", target = "appointment_name")
    Appointment toEntity(AddAppointmentRequest addAppointmentRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Appointment partialUpdate(UpdateAppointmentRequest updateAppointmentRequest, @MappingTarget Appointment appointment);


    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "department.departmentName", target = "departmentName")
    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "company.companyName", target = "companyName")
    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "event.eventName", target = "eventName")
    UpdateAppointmentResponse toUpdateAppointmentResponseDto(Appointment appointment);

    @Mapping(source = "appointmentId", target = "appointment.id")
    @Mapping(source = "studentId", target = "student.id")
    Attendance toEntity(GetAttendanceResponse getAttendanceResponse);

    @Mapping(source = "appointment.id", target = "appointmentId")
    @Mapping(source = "student.id", target = "studentId")
    GetAttendanceResponse toGetAttendanceResponseDto(Attendance attendance);

    @Mapping(source = "appointmentId", target = "appointment.id")
    @Mapping(source = "studentId", target = "student.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Attendance partialUpdate(GetAttendanceResponse getAttendanceResponse, @MappingTarget Attendance attendance);

    Collection<GetAttendanceResponse> toDto(Collection<Attendance> attendances);
}
    
