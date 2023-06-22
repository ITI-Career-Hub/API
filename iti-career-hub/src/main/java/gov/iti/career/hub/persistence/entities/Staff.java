package gov.iti.career.hub.persistence.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "staff")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Staff extends User {

    @Column(name = "first_name", nullable = true)
    private String firstName;

    @Column(name = "last_name", nullable = true)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Builder
    public Staff(Integer id, String username, String password, String email, String pictureUrl, Role role,
                 Boolean isActive, String firstName, String lastName, Department department) {
        super(id, username, password, email, pictureUrl, role, isActive);
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
    }
}
