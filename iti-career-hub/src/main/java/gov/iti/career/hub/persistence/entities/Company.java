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
    public Company(Integer id, String username, String password, String email,
                   Role role, String companyName, String description, String[] technologies,
                   String country, String state, String city, String street) {
        super(id, username, password, email, role);
        this.companyName = companyName;
        this.description = description;
        this.technologies = technologies;
        this.country = country;
        this.state = state;
        this.city = city;
        this.street = street;
    }

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "description", nullable = false)
    private String description;

    @Type(StringArrayType.class)
    @Column(
            name = "technologies",
            columnDefinition = "text[]"
            , nullable = false
    )
    private String[] technologies;
    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "street", nullable = false)
    private String street;

}
