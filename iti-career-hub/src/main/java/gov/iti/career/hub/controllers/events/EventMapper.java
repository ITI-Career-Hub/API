package gov.iti.career.hub.controllers.events;

import gov.iti.career.hub.controllers.companies.dtos.responses.GetEventForCompanyResponse;
import gov.iti.career.hub.controllers.departments.dtos.responses.GetEventForDepartmentResponse;
import gov.iti.career.hub.controllers.events.dtos.requests.AddEventRequest;
import gov.iti.career.hub.controllers.events.dtos.requests.UpdateEventRequest;
import gov.iti.career.hub.controllers.events.dtos.responses.AddEventResponse;
import gov.iti.career.hub.controllers.events.dtos.responses.GetEventResponse;
import gov.iti.career.hub.controllers.events.dtos.responses.UpdateEventResponse;
import gov.iti.career.hub.persistence.entities.Event;
import org.mapstruct.*;

import java.util.Collection;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EventMapper {

    GetEventResponse toGetAllEventsResponseDto(Event event);

    Collection<GetEventResponse> toGetAllEventsResponseDto(Collection<Event> event);

    AddEventResponse toAddEventResponseDto(Event event);

    Event toEntity(AddEventRequest addEventRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Event partialUpdate(UpdateEventRequest updateEventRequest, @MappingTarget Event event);

    UpdateEventResponse toUpdateEventResponseDto(Event event);
    Collection<GetEventForCompanyResponse> toGetEventForCompanyResponseDto(Collection<Event> events);
    Collection<GetEventForDepartmentResponse> toGetEventForDepartmentResponseDto(Collection<Event> events);
}