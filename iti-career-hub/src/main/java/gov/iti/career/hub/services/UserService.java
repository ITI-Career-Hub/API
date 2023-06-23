package gov.iti.career.hub.services;

import gov.iti.career.hub.persistence.entities.Student;
import gov.iti.career.hub.persistence.entities.User;
import gov.iti.career.hub.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public void deactivateUser(Integer id) {
        User user = userRepository.findById(id)
            .orElseThrow( () ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found")
            );

        user.setIsActive(false);
        userRepository.save(user);
    }
}
