package gov.iti.career.hub.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Student extends User{


    @Builder
    public Student(Integer id, String username, String password, String email, String pictureUrl, Role role,
                   Boolean isActive, String firstName, String lastName, String college, String phoneNumber,
                   Short intakeNumber, Short graduationYear, String resumeUrl, Department department,
                   Set<Attendance> attendances) {
        super(id, username, password, email, pictureUrl, role, isActive);
        this.firstName = firstName;
        this.lastName = lastName;
        this.college = college;
        this.phoneNumber = phoneNumber;
        this.intakeNumber = intakeNumber;
        this.graduationYear = graduationYear;
        this.resumeUrl = resumeUrl;
        this.department = department;
        this.attendances = attendances;
    }

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "college")
    private String college;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "intake_number", nullable = false)
    private Short intakeNumber;

    @Column(name = "graduation_year")
    private Short graduationYear;

    @Column(name = "resume_url", nullable = true)
    private String resumeUrl;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OneToMany(mappedBy = "student")
    private Set<Attendance> attendances = new HashSet<>();

}
