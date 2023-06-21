package gov.iti.career.hub.controllers.Departments.dtos.responses;

import gov.iti.career.hub.persistence.entities.enums.Discipline;

import java.io.Serializable;

public  record GetDepartmentResponse(Integer id, String departmentName, Discipline discipline, String mangerName, Integer mangerId) implements Serializable {}