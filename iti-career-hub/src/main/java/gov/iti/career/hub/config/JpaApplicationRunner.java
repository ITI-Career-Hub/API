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

        Role companyRole = Role.builder()
                .roleName("COMPANY")
                .build();

        Role staffRole = Role.builder()
                .roleName("STAFF")
                .build();

        Role studentRole = Role.builder()
                .roleName("STUDENT")
                .build();

        Department jets = Department.builder()
                .departmentName("JETS")
                .discipline(Discipline.SOFTWARE_ENGINEERING_AND_DEVELOPMENT)
                .build();

        Department mobile = Department.builder()
                .departmentName("MOBILE")
                .discipline(Discipline.SOFTWARE_ENGINEERING_AND_DEVELOPMENT)
                .build();

        Company company = Company.builder()
                .companyName("khaled's company")
                .username("khaleds")
                .password("password")
                .email("company@gmail.com")
                .role(admin)
                .city("Giza")
                .country("Egypt")
                .state("Sheikh Zayed")
                .street("Street 100")
                .description("Company Description")
                .technologies(new String[]{"JPA", "Springboot Framework"})
                .build();

        Staff staff = Staff.builder()
                .username("zyad yasser")
                .email("zyad.yasser@gmaiill.com")
                .password("password")
                .role(admin)
                .firstName("zyad")
                .lastName("yasser")
                .department(jets)
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
        roleRepository.save(companyRole);
        roleRepository.save(staffRole);
        roleRepository.save(studentRole);
        userRepository.save(student);
        userRepository.save(staff);
        userRepository.save(company);

    }
}
