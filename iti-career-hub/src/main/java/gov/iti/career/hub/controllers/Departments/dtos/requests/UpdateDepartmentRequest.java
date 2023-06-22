package gov.iti.career.hub.controllers.Departments.dtos.requests;

import gov.iti.career.hub.persistence.entities.enums.Discipline;

import java.io.Serializable;

public record UpdateDepartmentRequest(
        Integer id,
        String departmentName,
        Discipline discipline,
        Integer managerId) implements Serializable {}