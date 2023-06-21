package gov.iti.career.hub.controllers.Departments;

import gov.iti.career.hub.controllers.Departments.dtos.requests.CreateDepartmentRequest;
import gov.iti.career.hub.controllers.Departments.dtos.requests.UpdateDepartmentRequest;
import gov.iti.career.hub.controllers.Departments.dtos.responses.GetDepartmentResponse;
import gov.iti.career.hub.controllers.Departments.dtos.responses.UpdateDepartmentResponse;
import gov.iti.career.hub.persistence.entities.Department;
import gov.iti.career.hub.persistence.entities.Staff;
import gov.iti.career.hub.persistence.repositories.StaffRepository;
import gov.iti.career.hub.services.DepartmentService;
import gov.iti.career.hub.services.StaffService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/department")
@AllArgsConstructor
public class DepartmentController {
    final  private DepartmentService departmentService;
    final private StaffRepository staffRepository;


    @GetMapping
    public ResponseEntity<Collection<GetDepartmentResponse>> getAllDepartments() {
        Collection<GetDepartmentResponse> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }
    @GetMapping("/{id}")
    public ResponseEntity<GetDepartmentResponse> getDepartmentById(@PathVariable Integer id) {
        Optional<GetDepartmentResponse> departmentResponse = departmentService.getDepartmentById(id);
        return departmentResponse
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<UpdateDepartmentResponse> updateDepartment(@RequestBody UpdateDepartmentRequest updateRequest) {

        UpdateDepartmentResponse department = departmentService.updateDepartment(updateRequest);
        return ResponseEntity.ok(department);
    }
}
