package gov.iti.career.hub.persistence.entities;

import gov.iti.career.hub.persistence.entities.enums.RoleName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "role_name", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private RoleName roleName;
}
