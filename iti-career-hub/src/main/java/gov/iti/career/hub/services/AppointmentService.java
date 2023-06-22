package gov.iti.career.hub.services;

import java.util.*;

import gov.iti.career.hub.controllers.appointments.dtos.responses.GetAttendanceResponse;
import gov.iti.career.hub.persistence.entities.Attendance;
import gov.iti.career.hub.persistence.entities.enums.AttendanceStatus;
import gov.iti.career.hub.persistence.repositories.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import gov.iti.career.hub.controllers.appointments.AppointmentMapper;
import gov.iti.career.hub.controllers.appointments.dtos.requests.AddAppointmentRequest;
import gov.iti.career.hub.controllers.appointments.dtos.requests.UpdateAppointmentRequest;
import gov.iti.career.hub.controllers.appointments.dtos.responses.AddAppointmentResponse;
import gov.iti.career.hub.controllers.appointments.dtos.responses.GetAppointmentResponse;
import gov.iti.career.hub.controllers.appointments.dtos.responses.UpdateAppointmentResponse;
import gov.iti.career.hub.persistence.entities.Appointment;
import gov.iti.career.hub.persistence.repositories.AppointmentRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppointmentService {

    private final AppointmentMapper appointmentMapper;
    private final AppointmentRepository appointmentRepository;
    private final StudentRepository studentRepository;


    public Collection<GetAppointmentResponse> findAllAppointments(){

        return appointmentMapper.toGetAllAppointmentsResponseDto(appointmentRepository.findAll());

    }

    public Collection<GetAppointmentResponse> findCompanyAppointments(Integer companyId){
        return appointmentMapper.toGetAllAppointmentsResponseDto(appointmentRepository.findByCompany_Id(companyId));
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


    public Collection<GetAttendanceResponse> getAppointmentAttendances(Integer id){
        return appointmentMapper.toDto(
                appointmentRepository.findById(id).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Appointment Not Found")).getAttendances()
        );
    }
}
