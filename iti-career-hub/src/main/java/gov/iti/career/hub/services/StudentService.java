package gov.iti.career.hub.services;

import gov.iti.career.hub.controllers.students.StudentMapper;
import gov.iti.career.hub.controllers.students.dtos.requests.UpdateStudentRequest;
import gov.iti.career.hub.controllers.students.dtos.responses.GetAllStudentsResponse;
import gov.iti.career.hub.controllers.students.dtos.responses.UpdateStudentResponse;
import gov.iti.career.hub.persistence.entities.Student;
import gov.iti.career.hub.persistence.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final StudentMapper mapper = StudentMapper.INSTANCE;
    private final StudentRepository studentRepository;
    private final LocalValidatorFactoryBean validator;
    public Collection<GetAllStudentsResponse> findAllStudents(){
        return mapper.collectionToDto(studentRepository.findAll());
    }

    public UpdateStudentResponse updateStudent(Integer id, UpdateStudentRequest request) {
        Student student = studentRepository.findById(id)
                            .orElseThrow( () ->
                                new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")
                            );
        mapper.partialUpdate(request, student);
        return mapper.toUpdateStudentResponseDto(studentRepository.save(student));
    }
}
