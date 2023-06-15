package gov.iti.career.hub.persistence.entities;

import gov.iti.career.hub.persistence.entities.enums.Discipline;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "departments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "department_name", nullable = false)
    private String departmentName;

    @Column(name = "discipline", nullable = false)
    @Enumerated(EnumType.STRING)
    private Discipline discipline;

    @OneToOne
    @JoinColumn(name = "manager_id")
    private Staff manager;

}
