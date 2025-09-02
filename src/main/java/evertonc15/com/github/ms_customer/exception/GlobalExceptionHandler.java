package evertonc15.com.github.ms_customer.exception;

import evertonc15.com.github.ms_customer.dto.ErrorResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AlreadyExitsPetToCustomer.class)
    public ResponseEntity<ErrorResponseDTO> handleExistsPetToCustomer(AlreadyExitsPetToCustomer ex,
                                                                      WebRequest req) {
        var errorResponseDTO = ErrorResponseDTO.builder()
                .uriPatch(req.getDescription(false))
                .errorTime(LocalDateTime.now())
                .errorMessage(ex.getMessage())
                .httpErrorStatus(HttpStatus.BAD_REQUEST.value())
                .build();
        return new ResponseEntity<>(errorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        var validationErrorsMap = new HashMap<>();
        var errors = ex.getBindingResult().getAllErrors();

        errors.forEach(e -> {
            var fieldError = ((FieldError)e).getField();
            var valueError = e.getDefaultMessage();
            validationErrorsMap.put(fieldError, valueError);
        });

        return new ResponseEntity<>(validationErrorsMap, HttpStatus.BAD_REQUEST);

    }
}
