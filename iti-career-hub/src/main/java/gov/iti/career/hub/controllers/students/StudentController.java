package gov.iti.career.hub.controllers.students;

import gov.iti.career.hub.controllers.students.dtos.requests.UpdateStudentRequest;
import gov.iti.career.hub.controllers.students.dtos.responses.GetStudentResponse;
import gov.iti.career.hub.controllers.students.dtos.responses.UpdateStudentResponse;
import gov.iti.career.hub.services.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/student")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<Collection<GetStudentResponse>> findAllStudents(){
        return ResponseEntity
                .ok(studentService.findAllStudents());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetStudentResponse> findStudentById(@PathVariable Integer id){
        return ResponseEntity.ok(studentService.findStudentById(id));
    }
    @PutMapping("{id}")
    public ResponseEntity<UpdateStudentResponse> updateStudent(@PathVariable Integer id, @Valid @RequestBody UpdateStudentRequest request){
        return ResponseEntity.ok(studentService.updateStudent(id, request));
    }

}
