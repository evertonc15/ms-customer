package evertonc15.com.github.ms_customer.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    @NotBlank(message = "Customer name cannot be null pr empty")
    @Size(min = 2, max = 60, message = "O nome do cliente deve estar entre 2 e 60 caracteres")
    private String name;

    @CPF(message = "CPF invalido")
    private String cpf;

    @Email(message = "Email invalido")
    private String email;

    @Valid
    private ContactDTO contactDTO;

    private Set<PetDTO> petsDTO;

    private boolean active;

}
