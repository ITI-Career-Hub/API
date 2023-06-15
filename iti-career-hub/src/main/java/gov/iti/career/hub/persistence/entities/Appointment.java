package gov.iti.career.hub.persistence.entities;

import gov.iti.career.hub.persistence.entities.enums.InterviewType;
import io.hypersistence.utils.hibernate.type.array.StringArrayType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "appointments",
        uniqueConstraints = @UniqueConstraint(
            columnNames = {"department_id", "company_id", "event_id"}
        )
)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "appointment_name", nullable = false)
    private String appointment_name;

    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;

    @Column(name = "interview_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private InterviewType interviewType;

    @Type(StringArrayType.class)
    @Column(
            name = "interviewers",
            columnDefinition = "text[]"
            , nullable = false
    )
    private String[] interviewers;

    @Column(name = "room", nullable = false)
    private String room;

    @Column(name = "interview_notes")
    private String interviewNotes;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    @OneToMany(mappedBy = "appointment")
    private Set<Attendance> attendances = new HashSet<>();
}
