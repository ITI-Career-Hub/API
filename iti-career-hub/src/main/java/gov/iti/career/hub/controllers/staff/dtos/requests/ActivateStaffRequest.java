package gov.iti.career.hub.controllers.staff.dtos.requests;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Staff} entity
 */
public record ActivateStaffRequest(String password, String firstName, String lastName, String pictureUrl) implements Serializable {
}