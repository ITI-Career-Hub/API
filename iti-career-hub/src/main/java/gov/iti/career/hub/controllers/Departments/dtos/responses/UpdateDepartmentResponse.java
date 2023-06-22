package gov.iti.career.hub.controllers.Departments.dtos.responses;

import gov.iti.career.hub.persistence.entities.enums.Discipline;

import java.io.Serializable;

public record UpdateDepartmentResponse(Integer id, String departmentName, Discipline discipline, String managerName, Integer managerId) implements Serializable {}