package gov.iti.career.hub.controllers.students;

import gov.iti.career.hub.controllers.register.dtos.requests.RegisterStudentRequest;
import gov.iti.career.hub.controllers.students.dtos.requests.ActivateStudentRequest;
import gov.iti.career.hub.controllers.students.dtos.requests.UpdateStudentRequest;
import gov.iti.career.hub.controllers.students.dtos.responses.ActivateStudentResponse;
import gov.iti.career.hub.controllers.students.dtos.responses.GetStudentResponse;
import gov.iti.career.hub.controllers.students.dtos.responses.UpdateStudentResponse;
import gov.iti.career.hub.services.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<UpdateStudentResponse> updateStudent(@PathVariable Integer id,
                                                               @Valid @RequestBody UpdateStudentRequest request){
        System.out.println(request);
        return ResponseEntity.ok(studentService.updateStudent(id, request));
    }

    @PostMapping("/register")
    public ResponseEntity<ActivateStudentResponse> registerStudent(@RequestParam("token") String token,
                                                                   @RequestBody ActivateStudentRequest request) throws InvalidJwtException, MalformedClaimException {
        System.out.println(request);
        return ResponseEntity.ok(studentService.activateStudent(token, request));
    }

    @PostMapping("/validate")
    public ResponseEntity<RegisterStudentRequest> registerStudentData(@RequestParam("token") String token) throws InvalidJwtException, MalformedClaimException {
        return ResponseEntity.ok(studentService.registerStudentData(token));
    }
}
