package evertonc15.com.github.ms_customer.service;

import evertonc15.com.github.ms_customer.domain.Customer;
import evertonc15.com.github.ms_customer.dto.CustomerDTO;
import evertonc15.com.github.ms_customer.dto.PetDTO;
import evertonc15.com.github.ms_customer.exception.AlreadyExitsPetToCustomer;
import evertonc15.com.github.ms_customer.mapper.ContactMapper;
import evertonc15.com.github.ms_customer.mapper.CustomerMapper;
import evertonc15.com.github.ms_customer.mapper.PetMapper;
import evertonc15.com.github.ms_customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static evertonc15.com.github.ms_customer.constants.CustomerConstants.CUSTOMER_404_MESSAGE;
import static evertonc15.com.github.ms_customer.constants.CustomerConstants.CUSTOMER_MESSAGE_PET_EXISTS_400;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PetService petService;
    private final PetMapper petMapper;
    private final ContactMapper contactMapper;

    public void save(CustomerDTO customerDTO) {

        var customer = customerRepository.
                findByCpf(customerDTO.getCpf());
        if (!customer.isPresent()) {
            customer = Optional.of(customerMapper.dtoToEntity(customerDTO));
        } else {
            existsPetsToCustomer(customer.get(), customerDTO.getPetsDTO());
        }
        customerRepository.save(customer.get());

    }

    public void existsPetsToCustomer(Customer customer, Set<PetDTO> pets) {

        if (verifyPetsOfCustomer(customer, pets)) {
            log.error("m=existsPetsToCustomer, exists pet to customer with cpf = {}", customer.getCpf());
            throw new AlreadyExitsPetToCustomer(CUSTOMER_MESSAGE_PET_EXISTS_400);
        }

        customer.getPets().addAll(petMapper.dtoToEntity(pets));

    }

    private boolean verifyPetsOfCustomer(Customer customer, Set<PetDTO> pets) {

        var petsName = pets.stream().map(PetDTO::getName)
                .collect(Collectors.toSet());

        return  petService.existsPetFromCustomer(customer.getId(), petsName);

    }

    public Page<CustomerDTO> findAll(Pageable pageable) {

        var customer = customerRepository.findAll(pageable);

        return customer.map(c ->
                CustomerDTO.builder()
                        .name(c.getName())
                        .cpf(c.getCpf())
                        .email(c.getEmail())
                        .active(c.isActive())
                        .contactDTO(contactMapper.entityToDto(c.getContact()))
                        .petsDTO(petMapper.entityToDto(c.getPets()))
                        .build()
                );
    }

    public void updateCustomer(Long id, CustomerDTO customerDTO) {

        var customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                CUSTOMER_404_MESSAGE));

        if (customerDTO.getPetsDTO() != null && !customerDTO.getPetsDTO().isEmpty()) {
            if (verifyPetsOfCustomer(customer, customerDTO.getPetsDTO())) {
                throw new AlreadyExitsPetToCustomer(CUSTOMER_MESSAGE_PET_EXISTS_400);
            }
            customer.setPets(petMapper.dtoToEntity(customerDTO.getPetsDTO()));
        }

        if (customerDTO.getName() != null) {
            customer.setName(customerDTO.getName());
        }
        if (customerDTO.getCpf() != null) {
            String novoCpf = customerDTO.getCpf().trim();
            if (!novoCpf.equals(customer.getCpf())) {
                boolean cpfExists = customerRepository.existsByCpfAndIdNot(novoCpf, id);
                if (cpfExists) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Já existe um cliente cadastrado com esse CPF.");
                }
                customer.setCpf(novoCpf);
            }
        }
        if (customerDTO.getEmail() != null) {
            customer.setEmail(customerDTO.getEmail());
        }
        if (customerDTO.getContactDTO() != null) {
            customer.setContact(contactMapper.dtoToEntity(customerDTO.getContactDTO()));
        }

        customerRepository.save(customer);

    }

    public void changeStatus(Long id, boolean active) {
        var customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, CUSTOMER_404_MESSAGE));

        customer.setActive(active);
        customerRepository.save(customer);
    }

}
