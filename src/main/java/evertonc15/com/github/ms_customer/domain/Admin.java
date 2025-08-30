package evertonc15.com.github.ms_customer.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Admin extends Person {

    @Column(unique = true)
    private String employeeCode0;
}
