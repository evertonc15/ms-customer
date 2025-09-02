package evertonc15.com.github.ms_customer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetDTO {

    @NotBlank(message = "Pet's name cannot be null or empty")
    private String name;

    @NotBlank(message = "Pet's specie cannot be null or empty")
    private String species;
}
