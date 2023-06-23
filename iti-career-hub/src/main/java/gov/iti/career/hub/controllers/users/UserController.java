package gov.iti.career.hub.controllers.users;

import gov.iti.career.hub.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @DeleteMapping("{id}")
    public ResponseEntity deactivateStudent(@PathVariable Integer id){
        userService.deactivateUser(id);
        return ResponseEntity.status(200).build();
    }
}
