package gov.iti.career.hub.config;

import gov.iti.career.hub.persistence.repositories.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JpaApplicationRunner implements ApplicationRunner {

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {

    }
}
