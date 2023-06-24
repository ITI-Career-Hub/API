package gov.iti.career.hub.controllers.departments.dtos.responses;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Event} entity
 */
public record GetEventForDepartmentResponse(Long id, String eventName, LocalDate startDate, LocalDate endDate,
                                            String country, String state, String city,
                                            String street) implements Serializable {
}