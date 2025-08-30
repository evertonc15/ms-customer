package evertonc15.com.github.ms_customer.controller;

import evertonc15.com.github.ms_customer.constants.CustomerConstants;
import evertonc15.com.github.ms_customer.dto.CustomerDTO;
import evertonc15.com.github.ms_customer.dto.ResponseDTO;
import evertonc15.com.github.ms_customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static evertonc15.com.github.ms_customer.constants.CustomerConstants.CUSTOMER_201_STATUS;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor

public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> save(@RequestBody CustomerDTO customerDTO) {
        customerService.save(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseDTO.builder()
                        .message(CustomerConstants.CUSTOMER_201_MESSAGE)
                        .statusHttp(CUSTOMER_201_STATUS)
                .build());
    }
}
