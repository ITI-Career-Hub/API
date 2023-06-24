package gov.iti.career.hub.persistence.entities;

import io.hypersistence.utils.hibernate.type.array.StringArrayType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "companies")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Company extends User {

    @Builder
    public Company(Integer id, String username, String password, String email, String pictureUrl, Role role,
                   Boolean isActive, String companyName, String description, String[] technologies, String country,
                   String state, String city, String street) {
        super(id, username, password, email, pictureUrl, role, isActive);
        this.companyName = companyName;
        this.description = description;
        this.technologies = technologies;
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
    }

    @Column(name = "company_name", nullable = true)
    private String companyName;

    @Column(name = "description", nullable = true)
    private String description;

    @Type(StringArrayType.class)
    @Column(
            name = "technologies",
            columnDefinition = "text[]"
            , nullable = true
    )
    private String[] technologies;

    @Column(name = "country", nullable = true)
    private String country;

    @Column(name = "state", nullable = true)
    private String state;

    @Column(name = "city", nullable = true)
    private String city;

    @Column(name = "street", nullable = true)
    private String street;
}
