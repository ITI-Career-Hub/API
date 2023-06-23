package gov.iti.career.hub.services;

import gov.iti.career.hub.controllers.departments.DepartmentMapper;
import gov.iti.career.hub.controllers.departments.dtos.requests.UpdateDepartmentRequest;
import gov.iti.career.hub.controllers.departments.dtos.responses.GetDepartmentResponse;
import gov.iti.career.hub.controllers.departments.dtos.responses.UpdateDepartmentResponse;
import gov.iti.career.hub.persistence.entities.Department;
import gov.iti.career.hub.persistence.entities.Staff;
import gov.iti.career.hub.persistence.entities.enums.Discipline;
import gov.iti.career.hub.persistence.repositories.DepartmentRepository;
import gov.iti.career.hub.persistence.repositories.StaffRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentService {

    private  final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmetRepository;
    private final StaffRepository staffRepository;
    public GetDepartmentResponse createDepartment(String departmentName, Discipline discipline, Integer managerId) {
        Department department = new Department();

        department.setDepartmentName(departmentName);
        department.setDiscipline(discipline);
        Staff staff = staffRepository.findById(managerId)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Manger Not Found")
                );
        department.setManager(staff);
        return departmentMapper.toGetDepartmentResponsedto( departmetRepository.save(department));
    }

    public Optional<GetDepartmentResponse> getDepartmentById(Integer id) {
        return departmetRepository.findById(id)
                .map(departmentMapper::toGetDepartmentResponsedto);
    }

    public Collection<GetDepartmentResponse> getAllDepartments(Discipline discipline) {
        List<Department> departments = departmetRepository.findAll();
        if(discipline != null){
            departments = departments.stream()
                    .filter(department -> department.getDiscipline().equals(discipline))
                    .toList();
        }
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