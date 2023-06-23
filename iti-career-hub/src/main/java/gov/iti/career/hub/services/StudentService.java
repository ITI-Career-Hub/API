package gov.iti.career.hub.services;

import gov.iti.career.hub.controllers.register.RegistrationMapper;
import gov.iti.career.hub.controllers.register.dtos.requests.RegisterStudentRequest;
import gov.iti.career.hub.controllers.students.StudentMapper;
import gov.iti.career.hub.controllers.students.dtos.requests.ActivateStudentRequest;
import gov.iti.career.hub.controllers.students.dtos.requests.UpdateStudentRequest;
import gov.iti.career.hub.controllers.students.dtos.responses.ActivateStudentResponse;
import gov.iti.career.hub.controllers.students.dtos.responses.GetAllStudentsInDepartmentResponse;
import gov.iti.career.hub.controllers.students.dtos.responses.GetStudentResponse;
import gov.iti.career.hub.controllers.students.dtos.responses.UpdateStudentResponse;
import gov.iti.career.hub.persistence.entities.Role;
import gov.iti.career.hub.persistence.entities.Student;
import gov.iti.career.hub.persistence.repositories.RoleRepository;
import gov.iti.career.hub.persistence.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;
    private final RegistrationMapper mapper;
    private final JwtConsumer jwtConsumer;

    public GetStudentResponse findStudentById(Integer id){
        Student student = studentRepository.findById(id)
            .orElseThrow( () ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found")
            );
        return studentMapper.toGetStudentResponseDto(student);
    }
    public GetStudentResponse findStudentByUsername(String username){
        Student student = studentRepository.findByUsername(username)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found")
                );
        return studentMapper.toGetStudentResponseDto(student);
    }
    public Collection<GetStudentResponse> findAllStudents(){
        return studentMapper.collectionToDto(studentRepository.findAll());
    }

    public UpdateStudentResponse updateStudent(Integer id, UpdateStudentRequest request) {
        Student student = studentRepository.findById(id)
            .orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found")
            );
        studentMapper.partialUpdate(request, student);
        System.out.println(student);
        return studentMapper.toUpdateStudentResponseDto(studentRepository.save(student));
    }

    public ActivateStudentResponse activateStudent(String token, ActivateStudentRequest request)
            throws InvalidJwtException, MalformedClaimException {

        JwtClaims claims = jwtConsumer.processToClaims(token);
        Integer studentId = Integer.parseInt(claims.getSubject());
        Student student = studentRepository.findById(studentId)
            .orElseThrow( () ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found")
            );
        if(!student.getIsActive()){
            studentMapper.partialUpdate(request, student);
            student.setIsActive(true);
            System.out.println(student);
            return studentMapper.toActivateStudentResponseDto(studentRepository.save(student));
        }
        else throw new RuntimeException("Token Already Consumed Exception");

    }

    public RegisterStudentRequest registerStudentData(String token) throws InvalidJwtException, MalformedClaimException {
        JwtClaims claims = jwtConsumer.processToClaims(token);
        Integer studentId = Integer.parseInt(claims.getSubject());
        Student student = studentRepository.findById(studentId).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found")
        );
        if(student.getIsActive()){
            throw new RuntimeException("Token Already Consumed Exception");
        }
        return mapper.toRegisterStudentRequest(student);
    }
    public Collection<GetAllStudentsInDepartmentResponse> findAllActiveStudentsByDepartmentId(Integer id) {
        return studentMapper.toGetAllStudentsInDepartmentResponseDto(studentRepository.findAllActiveStudentsByDepartmentId(id));
    }
}
