package evertonc15.com.github.ms_customer.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorResponseDTO {

    private String uriPatch;

    private LocalDateTime errorTime;

    private String errorMessage;

    private Integer httpErrorStatus;

}
