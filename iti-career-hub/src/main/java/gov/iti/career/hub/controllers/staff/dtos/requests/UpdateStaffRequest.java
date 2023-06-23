package gov.iti.career.hub.controllers.staff.dtos.requests;

import lombok.Getter;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Staff} entity
 */

public record UpdateStaffRequest(String username, String email, Integer roleId, String firstName, String lastName,
                                 Integer departmentId, String pictureUrl) implements Serializable {
}