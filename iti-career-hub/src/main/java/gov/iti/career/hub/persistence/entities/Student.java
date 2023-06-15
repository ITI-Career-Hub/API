package gov.iti.career.hub.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student extends User{

    @Builder
    public Student(Integer id,
                   String username, String password, String email, Role role,
                   String firstName, String lastName, String college, String phoneNumber,
                   Short intakeNumber, Short graduationYear, Department department) {
        super(id, username, password, email, role);
        this.firstName = firstName;
        this.lastName = lastName;
        this.college = college;
        this.phoneNumber = phoneNumber;
        this.intakeNumber = intakeNumber;
        this.graduationYear = graduationYear;
        this.department = department;
    }

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "college", nullable = false)
    private String college;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "intake_number", nullable = false)
    private Short intakeNumber;

    @Column(name = "graduation_year", nullable = false)
    private Short graduationYear;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OneToMany(mappedBy = "student")
    private Set<Attendance> attendances = new HashSet<>();

}
