package gov.iti.career.hub.services;

import gov.iti.career.hub.controllers.events.EventMapper;
import gov.iti.career.hub.controllers.events.dtos.requests.AddEventRequest;
import gov.iti.career.hub.controllers.events.dtos.requests.UpdateEventRequest;
import gov.iti.career.hub.controllers.events.dtos.responses.AddEventResponse;
import gov.iti.career.hub.controllers.events.dtos.responses.GetEventResponse;
import gov.iti.career.hub.controllers.events.dtos.responses.UpdateEventResponse;
import gov.iti.career.hub.persistence.entities.Event;
import gov.iti.career.hub.persistence.entities.Student;
import gov.iti.career.hub.persistence.repositories.EventRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Service
@AllArgsConstructor
public class EventService {

    private final EventMapper mapper;
    private final EventRepository eventRepository;

    public Collection<GetEventResponse> findAllEvents(){
        return mapper.toGetAllEventsResponseDto(eventRepository.findAll());
    }

    public GetEventResponse findEvent(Integer id){
        return mapper.toGetAllEventsResponseDto(
                eventRepository.findById(id).orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found"))
        );
    }

    public AddEventResponse addEvent(AddEventRequest request){
        return mapper.toAddEventResponseDto(
                eventRepository.save(
                mapper.toEntity(request)
        ));
    }

    public void deleteEvent(Integer id){
        eventRepository.deleteById(id);
    }

    public UpdateEventResponse updateEvent(Integer id, UpdateEventRequest request){
        Event event = eventRepository.findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Not Found")
                );
        mapper.partialUpdate(request, event);
        return mapper.toUpdateEventResponseDto(eventRepository.save(event));
    }
}
