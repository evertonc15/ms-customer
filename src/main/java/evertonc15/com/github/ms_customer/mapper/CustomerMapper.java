package evertonc15.com.github.ms_customer.mapper;

import evertonc15.com.github.ms_customer.domain.Customer;
import evertonc15.com.github.ms_customer.dto.CustomerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerMapper {

    private final PetMapper petMapper;
    private final ContactMapper contactMapper;

    public Customer dtoToEntity(CustomerDTO customerDTO) {
        var customer = new Customer();
        customer.setCpf(customerDTO.getCpf());
        customer.setName(customerDTO.getName());
        customer.setEmail(customerDTO.getEmail());
        customer.setActive(true);
        customer.setContact(contactMapper.dtoToEntity(customerDTO.getContactDTO()));
        customer.setPets(petMapper.dtoToEntity(customerDTO.getPetsDTO()));

        return customer;

    }

}
