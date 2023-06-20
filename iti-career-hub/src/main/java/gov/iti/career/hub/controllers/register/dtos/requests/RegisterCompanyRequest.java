package gov.iti.career.hub.controllers.register.dtos.requests;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Company} entity
 */
public record RegisterCompanyRequest(String username, String email) implements Serializable {
}