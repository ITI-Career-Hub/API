package gov.iti.career.hub.config;

import gov.iti.career.hub.persistence.entities.*;
import gov.iti.career.hub.persistence.entities.enums.Discipline;
import gov.iti.career.hub.persistence.repositories.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JpaApplicationRunner implements ApplicationRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        Role admin = Role.builder()
                        .roleName("ADMIN")
                        .build();

        Department jets = Department.builder()
                .departmentName("JETS")
                .discipline(Discipline.SOFTWARE_ENGINEERING_AND_DEVELOPMENT)
                .build();

        Department mobile = Department.builder()
                .departmentName("MOBILE")
                .discipline(Discipline.SOFTWARE_ENGINEERING_AND_DEVELOPMENT)
                .build();

        Student student = Student.builder()
                .username("khaled")
                .password("password")
                .email("khaled.m.hisham@gmail.com")
                .college("university")
                .graduationYear((short) 2020)
                .intakeNumber((short) 43)
                .phoneNumber("01030503240")
                .role(admin)
                .department(jets)
                .firstName("khaled")
                .lastName("hisham")
                .build();

        departmentRepository.save(jets);
        departmentRepository.save(mobile);
        roleRepository.save(admin);
        userRepository.save(student);
    }
}
