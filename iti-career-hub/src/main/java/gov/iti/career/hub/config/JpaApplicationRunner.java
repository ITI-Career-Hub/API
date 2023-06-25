package gov.iti.career.hub.config;

import gov.iti.career.hub.persistence.entities.*;
import gov.iti.career.hub.persistence.entities.enums.AttendanceStatus;
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
    private final AttendanceRepository attendanceRepository;

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

        Department sd = Department.builder()
                .departmentName("Software Development")
                .discipline(Discipline.SOFTWARE_ENGINEERING_AND_DEVELOPMENT)
                .build();

        Department mobile = Department.builder()
                .departmentName("Cyber Security")
                .discipline(Discipline.INFRASTRUCTURE_NETWORK_AND_SECURITY_SERVICES)
                .build();

        Department ui = Department.builder()
                .departmentName("UI")
                .discipline(Discipline.CONTENT_DEVELOPMENT)
                .build();

        Company company = Company.builder()
                .companyName("Aman")
                .username("Aman")
                .password("password")
                .email("aman@gmail.com")
                .role(companyRole)
                .pictureUrl("https://www.hotlinesegypt.com/wp-content/uploads/2019/05/AMAN-E-Payments-19910-372x400.jpg")
                .city("Giza")
                .country("Egypt")
                .state("Sheikh Zayed")
                .street("Street 100")
                .description("Company Description")
                .technologies(new String[]{"JPA", "Springboot Framework"})
                .isActive(true)
                .build();

        Staff staff = Staff.builder()
                .username("Ziad")
                .email("zyad.yasser1@gmail.com")
                .password("password")
                .role(staffRole)
                .pictureUrl("https://i.imgur.com/Ig8XBfh.jpg")
                .firstName("Ziad")
                .lastName("Yasser")
                .department(jets)
                .isActive(true)
                .build();


        Staff admin = Staff.builder()
                .username("Admin")
                .email("zyad.yasser@gmail.com")
                .password("admin")
                .role(adminRole)
                .pictureUrl("https://www.pngmart.com/files/21/Admin-Profile-PNG-Clipart.png")
                .firstName("Career Hub")
                .lastName("Admin")
                .department(jets)
                .isActive(true)
                .build();

        Student student = Student.builder()
                .username("Khaled")
                .password("password")
                .email("khaled.m.hisham@gmail.com")
                .college("university")
                .graduationYear((short) 2020)
                .intakeNumber((short) 43)
                .phoneNumber("01030503240")
                .pictureUrl("https://i.imgur.com/q9gA9eM.jpg")
                .resumeUrl("https://fuchsia-meghann-34.tiiny.site/Ziad_Yasser_CV_Java-2023-06-25T12-01-37.776Z.pdf")
                .role(studentRole)
                .department(jets)
                .firstName("Khaled")
                .lastName("Hisham")
                .isActive(true)
                .build();

        Student kotb = Student.builder()
                .username("Kotb")
                .password("password")
                .email("khaled.m.hishams@gmail.com")
                .college("universitys")
                .graduationYear((short) 2020)
                .intakeNumber((short) 43)
                .phoneNumber("01030503240")
                .pictureUrl("https://i.imgur.com/dzsh45H.jpg")
                .resumeUrl("https://fuchsia-meghann-34.tiiny.site/Ziad_Yasser_CV_Java-2023-06-25T12-01-37.776Z.pdf")
                .role(studentRole)
                .department(jets)
                .firstName("Abdelrahman")
                .lastName("Kotb")
                .isActive(true)
                .build();

        Student sherbini = Student.builder()
                .username("Sherbini")
                .password("password")
                .email("AhmedMohamed@gmail.com")
                .college("universitys")
                .graduationYear((short) 2020)
                .intakeNumber((short) 43)
                .phoneNumber("01030503240")
                .pictureUrl("https://i.imgur.com/OIZkVUQ.jpg")
                .resumeUrl("https://fuchsia-meghann-34.tiiny.site/Ziad_Yasser_CV_Java-2023-06-25T12-01-37.776Z.pdf")
                .role(studentRole)
                .department(jets)
                .firstName("Ahmed")
                .lastName("Sherbini")
                .isActive(true)
                .build();

        Student zoz = Student.builder()
                .username("Ziad Yasser")
                .password("password")
                .email("ZyadYasser@gmail.com")
                .college("universitys")
                .graduationYear((short) 2020)
                .intakeNumber((short) 43)
                .phoneNumber("01030503240")
                .pictureUrl("https://i.imgur.com/Ig8XBfh.jpg")
                .resumeUrl("https://fuchsia-meghann-34.tiiny.site/Ziad_Yasser_CV_Java-2023-06-25T12-01-37.776Z.pdf")
                .role(studentRole)
                .department(jets)
                .firstName("Ziad")
                .lastName("Yasser")
                .isActive(true)
                .build();

        Event event1 = Event.builder()
                .eventName("Job Fair 43")
                .startDate(LocalDate.now().minusDays(3))
                .endDate(LocalDate.now())
                .city("Cairo")
                .state("Cairo")
                .country("Egypt")
                .street("Haram")
                .build();

        Event event2 = Event.builder()
                .eventName("Job Fair 42")
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
                .companyName("Fawry Payment Services")
                .username("Fawry")
                .password("password")
                .email("fawry@gmail.com")
                .role(companyRole)
                .pictureUrl("https://www.fawry.com/wp-content/uploads/2022/08/Myfawry-Vertical-01-233x300.png")
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
                .pictureUrl("https://www.vodafone.com/sites/default/files/2021-06/_VOIS.JPG")
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
                .username("Orange")
                .password("password")
                .email("orange@gmail.com")
                .role(companyRole)
                .pictureUrl("https://download.logo.wine/logo/Orange_Egypt/Orange_Egypt-Logo.wine.png")
                .city("Giza")
                .country("Egypt")
                .state("Sheikh Zayed")
                .street("Street 100")
                .description("Orange Description")
                .technologies(new String[]{"JPA", "Springboot Framework"})
                .isActive(true)
                .build();

        Appointment orangeAppointment1 = Appointment.builder()
            .appointmentName("Orange Java Interview")
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

        Appointment orangeAppointment3 = Appointment.builder()
                .appointmentName("Orange Cyber Interview")
                .appointmentDate(LocalDate.now())
                .interviewType(InterviewType.HR)
                .department(mobile)
                .company(orange)
                .event(event1)
                .interviewers(new String[]{"Khaled", "Hisham"})
                .interviewNotes("Khaled is Good")
                .room(room4)
                .isApproved(true)
                .build();

        Appointment orangeAppointment4 = Appointment.builder()
                .appointmentName("Orange UI Interview")
                .appointmentDate(LocalDate.now().minusDays(10))
                .interviewType(InterviewType.HR)
                .department(ui)
                .company(orange)
                .event(event1)
                .interviewers(new String[]{"Khaled", "Hisham"})
                .interviewNotes("Khaled is Good")
                .room(room4)
                .isApproved(true)
                .build();

        Appointment orangeAppointment2 = Appointment.builder()
                .appointmentName("Orange Cyber Interview")
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

        Appointment fawryAppointment1 = Appointment.builder()
                .appointmentName("Fawry Java Interview")
                .appointmentDate(LocalDate.of(1996, 10, 12))
                .interviewType(InterviewType.HR)
                .department(jets)
                .company(fawry)
                .event(event1)
                .interviewers(new String[]{"Khaled", "Hisham"})
                .interviewNotes("Khaled is Good")
                .room(room3)
                .isApproved(true)
                .build();

        Appointment voisAppointment1 = Appointment.builder()
                .appointmentName("Vois Cyber Security Interview")
                .appointmentDate(LocalDate.now().minusDays(11))
                .interviewType(InterviewType.HR)
                .department(mobile)
                .company(vois)
                .event(event1)
                .interviewers(new String[]{"Khaled", "Hisham"})
                .interviewNotes("Khaled is Good")
                .room(room3)
                .isApproved(true)
                .build();

        Attendance attendance = Attendance.builder()
                        .attendanceStatus(AttendanceStatus.PENDING)
                        .student(student)
                                .appointment(orangeAppointment1)
                                        .build();

        Attendance attendance1 = Attendance.builder()
                .attendanceStatus(AttendanceStatus.COMPLETED)
                .student(kotb)
                .appointment(orangeAppointment1)
                .build();

        Attendance attendance5 = Attendance.builder()
                .attendanceStatus(AttendanceStatus.PENDING)
                .student(sherbini)
                .appointment(orangeAppointment1)
                .build();

        Attendance attendance6 = Attendance.builder()
                .attendanceStatus(AttendanceStatus.PENDING)
                .student(zoz)
                .appointment(orangeAppointment1)
                .build();

        Attendance attendance2 = Attendance.builder()
                .attendanceStatus(AttendanceStatus.COMPLETED)
                .student(student)
                .appointment(orangeAppointment2)
                .build();

        Attendance attendance3 = Attendance.builder()
                .attendanceStatus(AttendanceStatus.PENDING)
                .student(student)
                .appointment(fawryAppointment1)
                .build();

        Attendance attendance4 = Attendance.builder()
                .attendanceStatus(AttendanceStatus.COMPLETED)
                .student(student)
                .appointment(voisAppointment1)
                .build();

        roomRepository.save(room1);
        roomRepository.save(room2);
        roomRepository.save(room3);
        roomRepository.save(room4);

        departmentRepository.save(jets);
        departmentRepository.save(sd);
        departmentRepository.save(ui);
        departmentRepository.save(mobile);

        roleRepository.save(adminRole);
        roleRepository.save(companyRole);
        roleRepository.save(studentRole);
        roleRepository.save(staffRole);

        userRepository.save(student);
        userRepository.save(staff);
        userRepository.save(company);
        userRepository.save(kotb);
        userRepository.save(sherbini);
        userRepository.save(zoz);
        userRepository.save(admin);

        userRepository.save(fawry);
        userRepository.save(orange);
        userRepository.save(vois);
        userRepository.save(vois);


        eventRepository.save(event1);
        eventRepository.save(event2);

        appointmentRepository.save(orangeAppointment1);
        appointmentRepository.save(orangeAppointment2);
        appointmentRepository.save(orangeAppointment3);
        appointmentRepository.save(orangeAppointment4);
        appointmentRepository.save(fawryAppointment1);
        appointmentRepository.save(voisAppointment1);

        attendanceRepository.save(attendance);
        attendanceRepository.save(attendance2);
        attendanceRepository.save((attendance3));
        attendanceRepository.save((attendance4));
        attendanceRepository.save((attendance1));
        attendanceRepository.save((attendance5));
        attendanceRepository.save((attendance6));
    }
}
