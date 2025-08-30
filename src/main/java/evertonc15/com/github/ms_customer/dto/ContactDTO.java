package evertonc15.com.github.ms_customer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactDTO {

    @NotBlank(message = "Mobile numb cannot be null or empty")
    @Size(min = 11, max = 11)
    private String mobileNumber;

    private String phone;

}
