package evertonc15.com.github.ms_customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AlreadyExitsPetToCustomer extends RuntimeException {

    public AlreadyExitsPetToCustomer(String message) {
        super(message);
    }

}
