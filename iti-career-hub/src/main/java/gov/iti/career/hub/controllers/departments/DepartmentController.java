package gov.iti.career.hub.controllers.departments;

import gov.iti.career.hub.controllers.departments.dtos.requests.CreateDepartmentRequest;
import gov.iti.career.hub.controllers.departments.dtos.requests.UpdateDepartmentRequest;
import gov.iti.career.hub.controllers.departments.dtos.responses.GetDepartmentResponse;
import gov.iti.career.hub.controllers.departments.dtos.responses.UpdateDepartmentResponse;
import gov.iti.career.hub.controllers.students.dtos.responses.GetAllStudentsInDepartmentResponse;
import gov.iti.career.hub.persistence.entities.enums.Discipline;
import gov.iti.career.hub.persistence.repositories.StaffRepository;
import gov.iti.career.hub.services.DepartmentService;
import gov.iti.career.hub.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/department")
@AllArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;
    private final StudentService studentService;


    @GetMapping
    public ResponseEntity<Collection<GetDepartmentResponse>> getAllDepartments(@RequestParam("discipline") Discipline discipline) {
        Collection<GetDepartmentResponse> departments = departmentService.getAllDepartments(discipline);
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetDepartmentResponse> getDepartmentById(@PathVariable Integer id) {
        Optional<GetDepartmentResponse> departmentResponse = departmentService.getDepartmentById(id);
        return departmentResponse
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<GetDepartmentResponse> createDepartment(@RequestBody CreateDepartmentRequest createRequest) {
        GetDepartmentResponse department = departmentService.createDepartment(
                createRequest.departmentName(),
                createRequest.discipline(),
                createRequest.managerId());
        return ResponseEntity.status(HttpStatus.CREATED).body(department);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UpdateDepartmentResponse> updateDepartment(@RequestBody UpdateDepartmentRequest updateRequest) {

        UpdateDepartmentResponse department = departmentService.updateDepartment(updateRequest);
        return ResponseEntity.ok(department);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Integer id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/student")
    public ResponseEntity<Collection<GetAllStudentsInDepartmentResponse>> findAllStudentsByDepartmentId(@PathVariable Integer id){
        return ResponseEntity.ok(studentService.findAllActiveStudentsByDepartmentId(id));
    }
}
