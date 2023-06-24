package gov.iti.career.hub.controllers.appointments;

import java.util.Collection;

import gov.iti.career.hub.controllers.appointments.dtos.requests.ActivateAppointmentRequest;
import gov.iti.career.hub.controllers.appointments.dtos.responses.*;
import gov.iti.career.hub.persistence.entities.enums.AttendanceStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gov.iti.career.hub.controllers.appointments.dtos.requests.AddAppointmentRequest;
import gov.iti.career.hub.controllers.appointments.dtos.requests.UpdateAppointmentRequest;

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

    @DeleteMapping("{id}")
    public ResponseEntity deleteAppointment(@PathVariable Integer id){
        appointmentService.deleteAppointment(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateAppointmentResponse> updateAppointment(@PathVariable Integer id, @Valid @RequestBody UpdateAppointmentRequest request){
        return ResponseEntity.ok(appointmentService.updateAppointment(id, request));
    }

    @GetMapping("{id}/attendance")
    public ResponseEntity<Collection<AttendanceResponse>> getAppointmentAttendances(@PathVariable Integer id){
        return ResponseEntity.ok(appointmentService.getAppointmentAttendances(id));
    }

    @PutMapping("{id}/approve")
    public ResponseEntity<ActivateAppointmentResponse> activateAppointment(@PathVariable Integer id, @Valid @RequestBody ActivateAppointmentRequest request){
        return ResponseEntity.ok(appointmentService.approveAppointment(id, request));
    }

    @PostMapping
    public ResponseEntity<AddAppointmentResponse> addAppointment(@Valid @RequestBody AddAppointmentRequest request){
        return ResponseEntity
                .ok(appointmentService.addAppointment(request));
    }

    @GetMapping("/attendance/student/{id}")
    public ResponseEntity<Collection<AttendanceResponse>> getStudentAttendance(@PathVariable Integer id){
        return ResponseEntity.ok(appointmentService.getStudentAttendance(id));
    }

    @PutMapping("/attendance/{id}")
    public ResponseEntity updateAttendanceStatus(@PathVariable Integer id, @RequestParam("newStatus") AttendanceStatus newStatus){
        appointmentService.changeAttendanceStatus(id, newStatus);
        return ResponseEntity.ok().build();
    }
}
