package gov.iti.career.hub.controllers.discipline;

import gov.iti.career.hub.persistence.entities.enums.Discipline;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/discipline")
public class DisciplineController {

    @GetMapping
    public ResponseEntity<Collection<Discipline>> getAllDisciplines(){
        return ResponseEntity.ok(List.of(Discipline.values()));
    }

}
