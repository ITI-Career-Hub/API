package gov.iti.career.hub.services;

import gov.iti.career.hub.controllers.students.StudentMapper;
import gov.iti.career.hub.controllers.students.dtos.requests.UpdateStudentRequest;
import gov.iti.career.hub.controllers.students.dtos.responses.GetStudentResponse;
import gov.iti.career.hub.controllers.students.dtos.responses.UpdateStudentResponse;
import gov.iti.career.hub.persistence.entities.Student;
import gov.iti.career.hub.persistence.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    public GetStudentResponse findStudentById(Integer id){
        Student student = studentRepository.findById(id)
            .orElseThrow( () ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")
            );
        return studentMapper.toGetStudentResponseDto(student);
    }
    public Collection<GetStudentResponse> findAllStudents(){
        return studentMapper.collectionToDto(studentRepository.findAll());
    }

    public UpdateStudentResponse updateStudent(Integer id, UpdateStudentRequest request) {
        Student student = studentRepository.findById(id)
                            .orElseThrow( () ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")
                            );
        studentMapper.partialUpdate(request, student);
        return studentMapper.toUpdateStudentResponseDto(studentRepository.save(student));
    }
}
