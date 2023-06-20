package gov.iti.career.hub.controllers.companies.dtos.requests;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Company} entity
 */
public record ActivateCompanyRequest(String password, String companyName, String description, String[] technologies,
                                     String country, String state, String city, String street) implements Serializable {
}