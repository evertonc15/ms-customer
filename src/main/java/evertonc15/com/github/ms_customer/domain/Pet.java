package evertonc15.com.github.ms_customer.domain;

import evertonc15.com.github.ms_customer.enums.Species;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Species species;

    @ManyToMany(mappedBy = "pets")
    private Set<Customer> customers;
}
