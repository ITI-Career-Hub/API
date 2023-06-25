package gov.iti.career.hub.services;

import java.util.*;

import gov.iti.career.hub.controllers.appointments.dtos.requests.ActivateAppointmentRequest;
import gov.iti.career.hub.controllers.appointments.dtos.responses.*;
import gov.iti.career.hub.controllers.companies.dtos.responses.GetAllAppointmentsByCompanyAndEvent;
import gov.iti.career.hub.controllers.departments.dtos.responses.GetAppointmentsForDepartmentInEvent;
import gov.iti.career.hub.persistence.entities.Attendance;
import gov.iti.career.hub.persistence.entities.enums.AttendanceStatus;
import gov.iti.career.hub.persistence.repositories.AttendanceRepository;
import gov.iti.career.hub.persistence.repositories.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import gov.iti.career.hub.controllers.appointments.AppointmentMapper;
import gov.iti.career.hub.controllers.appointments.dtos.requests.AddAppointmentRequest;
import gov.iti.career.hub.controllers.appointments.dtos.requests.UpdateAppointmentRequest;
import gov.iti.career.hub.persistence.entities.Appointment;
import gov.iti.career.hub.persistence.repositories.AppointmentRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppointmentService {

    private final AppointmentMapper appointmentMapper;
    private final AppointmentRepository appointmentRepository;
    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;


    public Collection<GetAppointmentResponse> findAllAppointments(){
        return appointmentMapper.toGetAllAppointmentsResponseDto(appointmentRepository.findAll());
    }

    public GetAppointmentResponse findAppointment(Integer id){
        return appointmentMapper.toGetAllAppointmentsResponseDto(
                appointmentRepository.findById(id).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment Not Found"))
        );
    }

    public AddAppointmentResponse addAppointment(AddAppointmentRequest request){
        Appointment appointment = appointmentMapper.toEntity(request);
        Set<Attendance> attendances = new HashSet<>();
        for(Integer id:request.studentIds()) {
            attendances.add(
                    Attendance.builder()
                            .student(studentRepository.findById(id)
                                    .orElseThrow(() ->
                                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment Not Found")))
                            .appointment(appointment)
                            .attendanceStatus(AttendanceStatus.PENDING)
                            .build()
            );
        }
        appointment.setAttendances(attendances);
        appointment.setIsApproved(false);
        return appointmentMapper.toAddAppointmentResponseDto(
                appointmentRepository.save(appointment));
    }

    public void deleteAppointment(Integer id){
        appointmentRepository.deleteById(id);
    }

    public UpdateAppointmentResponse updateAppointment(Integer id, UpdateAppointmentRequest request){
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment Not Found")
                );
        appointmentMapper.partialUpdate(request, appointment);
        return appointmentMapper.toUpdateAppointmentResponseDto(appointmentRepository.save(appointment));
    }


    public Collection<AttendanceResponse> getAppointmentAttendances(Integer id){
        return appointmentMapper.toAttendanceResponseDto(
                appointmentRepository.findById(id).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment Not Found")).getAttendances()
        );
    }

    public ActivateAppointmentResponse approveAppointment(Integer id, ActivateAppointmentRequest request) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment Not Found")
                );
        appointment.setIsApproved(true);
        appointmentMapper.partialUpdate(request, appointment);
        return appointmentMapper.toActivateAppointmentResponseDto(appointmentRepository.save(appointment));
    }

    public Collection<GetAllAppointmentsByCompanyAndEvent> getAllAppointmentsByCompanyAndEvent(Integer companyId, Integer eventId) {
        return appointmentMapper.toGetAllAppointmentsByCompanyAndEventDto(
                appointmentRepository.getAllAppointmentsByCompanyAndEvent(companyId, eventId)
        );
    }

    public Collection<AttendanceResponse> getStudentAttendance(Integer id){
        return appointmentMapper.toAttendanceResponseDto(
                attendanceRepository.findByStudentIdAndAppointmentIsApprovedTrue(id)
        );
    }

    public void changeAttendanceStatus(Integer id, AttendanceStatus status){
        attendanceRepository.updateAttendanceStatus(id, status);
    }

    public Collection<GetAppointmentsForDepartmentInEvent> getAppointmentsForDepartmentInEvent(Integer departmentId, Integer eventId) {
        return appointmentMapper.toGetAppointmentsForDepartmentInEventDto(
                appointmentRepository.findAppointmentForDepartmentInEvent(departmentId, eventId)
        );
    }

    public Collection<GetAppointmentResponse> getAppointmentsForCompanyAndEvent(Integer companyId, Integer eventId) {
        return appointmentMapper.toGetAllAppointmentsResponseDto(
                appointmentRepository.findByCompanyIdAndEventId(companyId, eventId)
        );
    }

    public Collection<GetAppointmentResponse> getAppointmentsForEvent(Integer eventId) {
        return appointmentMapper.toGetAllAppointmentsResponseDto(
                appointmentRepository.findByEventId( eventId)
        );
    }
}
