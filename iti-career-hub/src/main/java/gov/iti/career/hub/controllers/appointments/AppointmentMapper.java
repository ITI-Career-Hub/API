package gov.iti.career.hub.controllers.appointments;

import java.util.Collection;

import gov.iti.career.hub.controllers.appointments.dtos.requests.ActivateAppointmentRequest;
import gov.iti.career.hub.controllers.appointments.dtos.responses.*;
import gov.iti.career.hub.controllers.companies.dtos.responses.GetAllAppointmentsByCompanyAndEvent;
import gov.iti.career.hub.persistence.entities.Attendance;
import gov.iti.career.hub.persistence.repositories.RoomRepository;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

import gov.iti.career.hub.controllers.appointments.dtos.requests.AddAppointmentRequest;
import gov.iti.career.hub.controllers.appointments.dtos.requests.UpdateAppointmentRequest;
import gov.iti.career.hub.persistence.entities.Appointment;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class AppointmentMapper {

    @Autowired
    private RoomRepository roomRepository;

    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "department.departmentName", target = "departmentName")
    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "company.companyName", target = "companyName")
    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "event.eventName", target = "eventName")
    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "room.name", target = "roomName")
    public abstract GetAppointmentResponse toGetAllAppointmentsResponseDto(Appointment appointment);

    @InheritConfiguration(name = "toGetAllAppointmentsResponseDto")
    public abstract Collection<GetAppointmentResponse> toGetAllAppointmentsResponseDto(Collection<Appointment> appointment);

    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "department.departmentName", target = "departmentName")
    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "company.companyName", target = "companyName")
    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "event.eventName", target = "eventName")
    @Mapping(source = "appointmentName", target = "appointmentName")
    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "room.name", target = "roomName")
    public abstract AddAppointmentResponse toAddAppointmentResponseDto(Appointment appointment);

    @Mapping(source = "departmentId", target = "department.id")
    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "eventId", target = "event.id")
    @Mapping(source = "appointmentName", target = "appointmentName")
    public abstract Appointment toEntity(AddAppointmentRequest addAppointmentRequest);

    @Mapping(source = "roomId", target = "room.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Appointment partialUpdate(UpdateAppointmentRequest updateAppointmentRequest, @MappingTarget Appointment appointment);


    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "department.departmentName", target = "departmentName")
    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "company.companyName", target = "companyName")
    @Mapping(source = "event.id", target = "eventId")
    @Mapping(source = "event.eventName", target = "eventName")
    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "room.name", target = "roomName")
    public abstract UpdateAppointmentResponse toUpdateAppointmentResponseDto(Appointment appointment);

    @Mapping(source = "appointmentId", target = "appointment.id")
    @Mapping(source = "studentId", target = "student.id")
    public abstract Attendance toEntity(GetAttendanceResponse getAttendanceResponse);

    @Mapping(source = "appointment.id", target = "appointmentId")
    @Mapping(source = "student.id", target = "studentId")
    public abstract GetAttendanceResponse toGetAttendanceResponseDto(Attendance attendance);

    @Mapping(source = "appointmentId", target = "appointment.id")
    @Mapping(source = "studentId", target = "student.id")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public abstract Attendance partialUpdate(GetAttendanceResponse getAttendanceResponse, @MappingTarget Attendance attendance);

    public abstract Collection<GetAttendanceResponse> toDto(Collection<Attendance> attendances);

    @Mapping(source = "roomId", target = "room.id")
    public abstract Appointment toEntity(ActivateAppointmentRequest activateAppointmentRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "roomId", target = "room.id")
    public abstract Appointment partialUpdate(ActivateAppointmentRequest activateAppointmentRequest, @MappingTarget Appointment appointment);


    @Mapping(source = "company.companyName", target = "companyName")
    @Mapping(source = "company.email", target = "companyEmail")
    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "department.departmentName", target = "departmentName")
    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "room.name", target = "roomName")
    @Mapping(source = "room.id", target = "roomId")
    public abstract ActivateAppointmentResponse toActivateAppointmentResponseDto(Appointment appointment);

    @Mapping(source = "department.departmentName", target = "departmentName")
    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "room.name", target = "roomName")
    @Mapping(source = "room.id", target = "roomId")
    public abstract GetAllAppointmentsByCompanyAndEvent toGetAllAppointmentsByCompanyAndEventDto(Appointment appointment);

    public abstract Collection<GetAllAppointmentsByCompanyAndEvent> toGetAllAppointmentsByCompanyAndEventDto(Collection<Appointment> appointments);

    @Mapping(source = "appointmentCompanyCompanyName", target = "appointment.company.companyName")
    @Mapping(source = "appointmentCompanyId", target = "appointment.company.id")
    @Mapping(source = "appointmentRoomName", target = "appointment.room.name")
    @Mapping(source = "appointmentRoomId", target = "appointment.room.id")
    @Mapping(source = "appointmentInterviewType", target = "appointment.interviewType")
    @Mapping(source = "appointmentAppointmentDate", target = "appointment.appointmentDate")
    @Mapping(source = "appointmentId", target = "appointment.id")
    @Mapping(source = "studentId", target = "student.id")
    @Mapping(source = "studentPictureUrl", target = "student.pictureUrl")
    @Mapping(source = "studentFirstName", target = "student.firstName")
    @Mapping(source = "studentLastName", target = "student.lastName")
    @Mapping(source = "studentResumeUrl", target = "student.resumeUrl")
    @Mapping(source = "appointmentCompanyPictureUrl", target = "appointment.company.pictureUrl")
    abstract Attendance toAttendanceEntity(AttendanceResponse attendanceResponse);

    @InheritInverseConfiguration(name = "toAttendanceEntity")
    public abstract AttendanceResponse toAttendanceResponseDto(Attendance attendance);

    @InheritInverseConfiguration(name = "toAttendanceEntity")
    public abstract Collection<AttendanceResponse> toAttendanceResponseDto(Collection<Attendance> attendance);

    @InheritConfiguration(name = "toAttendanceEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    abstract Attendance partialUpdate(AttendanceResponse attendanceResponse, @MappingTarget Attendance attendance);

//    @Named("fetchRoomByName")
//    protected Room fetchRoomById(Integer roomId) {
//        return roomRepository.findById(roomId)
//                .orElseThrow(() ->
//                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Room Not Found"));
//    }

    String concatenateFields(String field1, String field2) {
        return field1 + field2;
    }
}
    
