package evertonc15.com.github.ms_customer.service;

import evertonc15.com.github.ms_customer.domain.Customer;
import evertonc15.com.github.ms_customer.dto.CustomerDTO;
import evertonc15.com.github.ms_customer.dto.PetDTO;
import evertonc15.com.github.ms_customer.exception.AlreadyExitsPetToCustomer;
import evertonc15.com.github.ms_customer.mapper.CustomerMapper;
import evertonc15.com.github.ms_customer.mapper.PetMapper;
import evertonc15.com.github.ms_customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static evertonc15.com.github.ms_customer.constants.CustomerConstants.EXIST_PET_TO_CUSTOMER;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final PetService petService;
    private final PetMapper petMapper;

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
            throw new AlreadyExitsPetToCustomer(EXIST_PET_TO_CUSTOMER);
        }
        customer.getPets().addAll(petMapper.dtoToEntity(pets));

    }

    private boolean verifyPetsOfCustomer(Customer customer, Set<PetDTO> pets) {

        var petsName = pets.stream().map(PetDTO::getName)
                .collect(Collectors.toSet());

        return  petService.existsPetFromCustomer(customer.getId(), petsName);

    }

}
