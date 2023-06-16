package gov.iti.career.hub.controllers.events;

import gov.iti.career.hub.controllers.events.dtos.requests.AddEventRequest;
import gov.iti.career.hub.controllers.events.dtos.requests.UpdateEventRequest;
import gov.iti.career.hub.controllers.events.dtos.responses.AddEventResponse;
import gov.iti.career.hub.controllers.events.dtos.responses.GetEventResponse;
import gov.iti.career.hub.controllers.events.dtos.responses.UpdateEventResponse;
import gov.iti.career.hub.controllers.students.dtos.requests.UpdateStudentRequest;
import gov.iti.career.hub.controllers.students.dtos.responses.UpdateStudentResponse;
import gov.iti.career.hub.services.EventService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/event")
@AllArgsConstructor
public class EventController {

    private final EventService eventService;

    @GetMapping
    public ResponseEntity<Collection<GetEventResponse>> findAllEvents(){
        return ResponseEntity
                .ok(eventService.findAllEvents());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetEventResponse> findEvent(@PathVariable Integer id){
        return ResponseEntity
                .ok(eventService.findEvent(id));
    }

    @PostMapping
    public ResponseEntity<AddEventResponse> addEvent(@Valid @RequestBody AddEventRequest request){
        return ResponseEntity
                .ok(eventService.addEvent(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteEvent(@PathVariable Integer id){
        eventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateEventResponse> updateEvent(@PathVariable Integer id, @Valid @RequestBody UpdateEventRequest request){
        return ResponseEntity.ok(eventService.updateEvent(id, request));
    }
}
