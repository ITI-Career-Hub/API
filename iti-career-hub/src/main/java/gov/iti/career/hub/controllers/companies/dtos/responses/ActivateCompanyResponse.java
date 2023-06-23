package gov.iti.career.hub.controllers.companies.dtos.responses;

import gov.iti.career.hub.persistence.entities.enums.RoleName;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Company} entity
 */
public record ActivateCompanyResponse(Integer id, String username, String email, Integer roleId, RoleName roleRoleName,
                                      String companyName, String description, String[] technologies, String country,
                                      String state, String city, String street, String pictureUrl) implements Serializable {
}