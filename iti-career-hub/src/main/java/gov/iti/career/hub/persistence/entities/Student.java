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
    public Student(Integer id,
                   String username, String password, String email, Role role,
                   String firstName, String lastName, String college, String phoneNumber,
                   Short intakeNumber, Short graduationYear, Department department) {
        super(id, username, password, email, role, false);
        this.firstName = firstName;
        this.lastName = lastName;
        this.college = college;
        this.phoneNumber = phoneNumber;
        this.intakeNumber = intakeNumber;
        this.graduationYear = graduationYear;
        this.department = department;
//        super.setRole(new Role(4, "STUDENT"));
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

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @OneToMany(mappedBy = "student")
    private Set<Attendance> attendances = new HashSet<>();

}
