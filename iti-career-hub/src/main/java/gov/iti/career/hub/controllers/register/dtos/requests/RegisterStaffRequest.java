package gov.iti.career.hub.controllers.register.dtos.requests;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Staff} entity
 */
public record RegisterStaffRequest(String username, String email, Integer departmentId) implements Serializable {
}