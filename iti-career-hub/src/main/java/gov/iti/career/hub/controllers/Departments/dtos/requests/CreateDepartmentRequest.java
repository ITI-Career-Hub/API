package gov.iti.career.hub.controllers.Departments.dtos.requests;

import gov.iti.career.hub.persistence.entities.enums.Discipline;

import java.io.Serializable;

public record CreateDepartmentRequest (String departmentName,
                                       Discipline disciplin,
                                       Integer mangerId) implements Serializable {}
