package gov.iti.career.hub.services;

import java.util.Collection;

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
        return appointmentMapper.toAddAppointmentResponseDto(
                appointmentRepository.save(
                appointmentMapper.toEntity(request)
        ));
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


    
}
