package gov.iti.career.hub.controllers.staff.dtos.responses;

import gov.iti.career.hub.persistence.entities.enums.Discipline;

import java.io.Serializable;

/**
 * A DTO for the {@link gov.iti.career.hub.persistence.entities.Staff} entity
 */
public record GetStaffResponse(Integer id, String username, String password, String email, Integer roleId,
                               String roleName, String firstName, String lastName, Integer departmentId,
                               String departmentName, Discipline departmentDiscipline)
        implements Serializable {}