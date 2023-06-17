package gov.iti.career.hub.controllers.companies.dtos.responses;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Company} entity
 */
public record GetAllCompaniesResponse(Integer id, String username, String email, Integer roleId, String roleName,
                                      String companyName, String description, String[] technologies, String country,
                                      String state, String city, String street) implements Serializable {
}