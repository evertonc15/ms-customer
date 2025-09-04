package evertonc15.com.github.ms_customer.controller;

import evertonc15.com.github.ms_customer.constants.CustomerConstants;
import evertonc15.com.github.ms_customer.dto.CustomerDTO;
import evertonc15.com.github.ms_customer.dto.ResponseDTO;
import evertonc15.com.github.ms_customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static evertonc15.com.github.ms_customer.constants.CustomerConstants.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor

public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> save(@Valid @RequestBody CustomerDTO customerDTO) {
        customerService.save(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseDTO.builder()
                        .message(CustomerConstants.CUSTOMER_201_MESSAGE)
                        .statusHttp(CUSTOMER_201_STATUS)
                        .build());
    }

    @GetMapping("/findAll")
    public Page<CustomerDTO> findAll(Pageable pageable) {
        return customerService.findAll(pageable);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> update(@PathVariable("id") Long id,
                                              @Valid @RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseDTO.builder()
                        .message(CUSTOMER_200_UPDATE_MESSAGE)
                        .statusHttp(CUSTOMER_200_STATUS)
                        .build());
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ResponseDTO> changeStatus(
            @PathVariable("id") Long id,
            @RequestParam(name = "active") boolean active) {

        customerService.changeStatus(id, active);

        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseDTO.builder()
                        .message(active
                                ? CUSTOMER_200_REACTIVATE_MESSAGE
                                : CUSTOMER_200_INACTIVATE_MESSAGE)
                        .statusHttp(CUSTOMER_200_STATUS)
                        .build()
        );
    }

}
