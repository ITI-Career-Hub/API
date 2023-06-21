package gov.iti.career.hub.services;

import gov.iti.career.hub.controllers.Departments.DepartmentMapper;
import gov.iti.career.hub.controllers.Departments.dtos.requests.UpdateDepartmentRequest;
import gov.iti.career.hub.controllers.Departments.dtos.responses.GetDepartmentResponse;
import gov.iti.career.hub.controllers.Departments.dtos.responses.UpdateDepartmentResponse;
import gov.iti.career.hub.persistence.entities.Department;
import gov.iti.career.hub.persistence.entities.Staff;
import gov.iti.career.hub.persistence.entities.enums.Discipline;
import gov.iti.career.hub.persistence.repositories.DepartmentRepository;
import gov.iti.career.hub.persistence.repositories.StaffRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentService {

    private  final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmetRepository;
    private final StaffService staffService;
    public GetDepartmentResponse createDepartment(String departmentName, Discipline discipline, Staff manager) {
        Department department = new Department();
        department.setDepartmentName(departmentName);
        department.setDiscipline(discipline);
        department.setManager(manager);
        return departmentMapper.toGetDepartmentResponsedto( departmetRepository.save(department));
    }

    public Optional<GetDepartmentResponse> getDepartmentById(Integer id) {
        return departmetRepository.findById(id)
                .map(departmentMapper::toGetDepartmentResponsedto);
    }

    public Collection<GetDepartmentResponse> getAllDepartments() {
        List<Department> departments = departmetRepository.findAll();
        return departmentMapper.toGetDepartmentsResponseDto(departments);
    }

    public UpdateDepartmentResponse updateDepartment(UpdateDepartmentRequest updateDepartmentRequest) {
        Department department = departmentMapper.ToEntity(updateDepartmentRequest);
        return departmentMapper.toupdateDepartmentResponsedto(departmetRepository.save(department));
    }

    public void deleteDepartment(Integer id) {
        departmetRepository.deleteById(id);
    }




}