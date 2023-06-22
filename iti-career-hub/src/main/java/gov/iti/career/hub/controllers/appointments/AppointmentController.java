package gov.iti.career.hub.controllers.appointments;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.iti.career.hub.controllers.appointments.dtos.requests.AddAppointmentRequest;
import gov.iti.career.hub.controllers.appointments.dtos.requests.UpdateAppointmentRequest;
import gov.iti.career.hub.controllers.appointments.dtos.responses.AddAppointmentResponse;
import gov.iti.career.hub.controllers.appointments.dtos.responses.GetAppointmentResponse;
import gov.iti.career.hub.controllers.appointments.dtos.responses.UpdateAppointmentResponse;

import gov.iti.career.hub.services.AppointmentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentController {

   private final AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<Collection<GetAppointmentResponse>> findAllAppointments(){
        return ResponseEntity
                .ok(appointmentService.findAllAppointments());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetAppointmentResponse> findAppointment(@PathVariable Integer id){
        return ResponseEntity
                .ok(appointmentService.findAppointment(id));
    }

    @PostMapping
    public ResponseEntity<AddAppointmentResponse> addAppointment(@Valid @RequestBody AddAppointmentRequest request){
        return ResponseEntity
                .ok(appointmentService.addAppointment(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteAppointment(@PathVariable Integer id){
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateAppointmentResponse> updateAppointment(@PathVariable Integer id, @Valid @RequestBody UpdateAppointmentRequest request){
        return ResponseEntity.ok(appointmentService.updateAppointment(id, request));
    } 
    
}
