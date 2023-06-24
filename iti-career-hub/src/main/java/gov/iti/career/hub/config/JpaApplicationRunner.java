package gov.iti.career.hub.config;

import gov.iti.career.hub.persistence.entities.*;
import gov.iti.career.hub.persistence.entities.enums.Discipline;
import gov.iti.career.hub.persistence.entities.enums.InterviewType;
import gov.iti.career.hub.persistence.entities.enums.RoleName;
import gov.iti.career.hub.persistence.repositories.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class JpaApplicationRunner implements ApplicationRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DepartmentRepository departmentRepository;
    private final EventRepository eventRepository;
    private final RoomRepository roomRepository;
    private final AppointmentRepository appointmentRepository;

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        Role adminRole = Role.builder()
                .roleName(RoleName.ADMIN)
                .build();

        Role studentRole = Role.builder()
                .roleName(RoleName.STUDENT)
                .build();

        Role companyRole = Role.builder()
                .roleName(RoleName.COMPANY)
                .build();

        Role staffRole = Role.builder()
                .roleName(RoleName.STAFF)
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
                .role(companyRole)
                .pictureUrl("company.png")
                .city("Giza")
                .country("Egypt")
                .state("Sheikh Zayed")
                .street("Street 100")
                .description("Company Description")
                .technologies(new String[]{"JPA", "Springboot Framework"})
                .isActive(true)
                .build();

        Staff staff = Staff.builder()
                .username("zyad yasser")
                .email("zyad.yasser@gmaiill.com")
                .password("password")
                .role(staffRole)
                .pictureUrl("staff.png")
                .firstName("zyad")
                .lastName("yasser")
                .department(jets)
                .isActive(true)
                .build();

        Student student = Student.builder()
                .username("khaled")
                .password("password")
                .email("khaled.m.hisham@gmail.com")
                .college("university")
                .graduationYear((short) 2020)
                .intakeNumber((short) 43)
                .phoneNumber("01030503240")
                .pictureUrl("student.png")
                .resumeUrl("resumeURLLLLLLLLLL")
                .role(studentRole)
                .department(jets)
                .firstName("khaled")
                .lastName("hisham")
                .isActive(true)
                .build();

        Student khaleds = Student.builder()
                .username("khaleds")
                .password("password")
                .email("khaled.m.hishams@gmail.com")
                .college("universitys")
                .graduationYear((short) 2020)
                .intakeNumber((short) 43)
                .phoneNumber("01030503240")
                .pictureUrl("students.png")
                .resumeUrl("resumeURLLLLLLLLLLs")
                .role(studentRole)
                .department(jets)
                .firstName("khaleds")
                .lastName("hishams")
                .isActive(true)
                .build();

        Event event1 = Event.builder()
                .eventName("Fair43")
                .startDate(LocalDate.now().minusDays(3))
                .endDate(LocalDate.now())
                .city("Cairo")
                .state("Cairo")
                .country("Egypt")
                .street("Haram")
                .build();

        Event event2 = Event.builder()
                .eventName("Fair42")
                .startDate(LocalDate.now().minusDays(3))
                .endDate(LocalDate.now())
                .city("Cairo")
                .state("Cairo")
                .country("Egypt")
                .street("Haram")
                .build();

        Room room1 = Room.builder()
                .name("1029")
                .build();
        Room room2 = Room.builder()
                .name("1030")
                .build();
        Room room3 = Room.builder()
                .name("1031")
                .build();
        Room room4 = Room.builder()
                .name("1032")
                .build();


        Company fawry = Company.builder()
                .companyName("Fawry")
                .username("Fawry Payment Services")
                .password("password")
                .email("fawry@gmail.com")
                .role(companyRole)
                .pictureUrl("fawry.png")
                .city("Giza")
                .country("Egypt")
                .state("Sheikh Zayed")
                .street("Street 100")
                .description("Fawry Description")
                .technologies(new String[]{"JPA", "Springboot Framework"})
                .isActive(true)
                .build();

        Company vois = Company.builder()
                .companyName("_VOIS")
                .username("VOIS")
                .password("password")
                .email("vois@gmail.com")
                .role(companyRole)
                .pictureUrl("vois.png")
                .city("Giza")
                .country("Egypt")
                .state("Sheikh Zayed")
                .street("Street 100")
                .description("VOIS Description")
                .technologies(new String[]{"JPA", "Springboot Framework"})
                .isActive(true)
                .build();

        Company orange = Company.builder()
                .companyName("Orange")
                .username("Orange Innov")
                .password("password")
                .email("orange@gmail.com")
                .role(companyRole)
                .pictureUrl("orange.png")
                .city("Giza")
                .country("Egypt")
                .state("Sheikh Zayed")
                .street("Street 100")
                .description("Orange Description")
                .technologies(new String[]{"JPA", "Springboot Framework"})
                .isActive(true)
                .build();

        Appointment orangeAppointment1 = Appointment.builder()
            .appointmentName("orange_appointment_jets43")
            .appointmentDate(LocalDate.now())
            .interviewType(InterviewType.HR)
            .department(jets)
            .company(orange)
            .event(event1)
            .interviewers(new String[]{"Khaled", "Hisham"})
            .interviewNotes("Khaled is Good")
            .room(room3)
            .isApproved(true)
            .build();

        Appointment orangeAppointment2 = Appointment.builder()
                .appointmentName("orange_appointment_jets42")
                .appointmentDate(LocalDate.now())
                .interviewType(InterviewType.TECHNICAL)
                .department(mobile)
                .company(orange)
                .event(event2)
                .interviewers(new String[]{"Khaled", "Hisham"})
                .interviewNotes("Khaled is Good")
                .room(room2)
                .isApproved(true)
                .build();



        roomRepository.save(room1);
        roomRepository.save(room2);
        roomRepository.save(room3);
        roomRepository.save(room4);

        departmentRepository.save(jets);
        departmentRepository.save(mobile);

        roleRepository.save(adminRole);
        roleRepository.save(companyRole);
        roleRepository.save(studentRole);
        roleRepository.save(staffRole);

        userRepository.save(student);
        userRepository.save(staff);
        userRepository.save(company);
        userRepository.save(khaleds);

        userRepository.save(fawry);
        userRepository.save(orange);
        userRepository.save(vois);

        eventRepository.save(event1);
        eventRepository.save(event2);

        appointmentRepository.save(orangeAppointment1);
        appointmentRepository.save(orangeAppointment2);
    }
}
