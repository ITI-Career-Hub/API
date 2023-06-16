package gov.iti.career.hub.controllers.staff;

import gov.iti.career.hub.controllers.staff.dtos.requests.UpdateStaffRequest;
import gov.iti.career.hub.controllers.staff.dtos.responses.GetAllStaffResponse;
import gov.iti.career.hub.controllers.staff.dtos.responses.UpdateStaffResponse;
import gov.iti.career.hub.services.StaffService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/staff")
@AllArgsConstructor
public class StaffController {

    private final StaffService staffService;
    @GetMapping
    public ResponseEntity<Collection<GetAllStaffResponse>> findAllStudents(){
        return ResponseEntity
                .ok(staffService.findAllStaff());
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateStaffResponse> updateStudent(@PathVariable Integer id, @Valid @RequestBody UpdateStaffRequest request){
        return ResponseEntity.ok(staffService.updateStaff(id, request));
    }
}
