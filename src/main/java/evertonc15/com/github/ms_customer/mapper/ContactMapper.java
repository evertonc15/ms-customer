package evertonc15.com.github.ms_customer.mapper;

import evertonc15.com.github.ms_customer.domain.Contact;
import evertonc15.com.github.ms_customer.dto.ContactDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ContactMapper {

    Contact dtoToEntity(ContactDTO contactsDTO);

    ContactDTO entityToDto(Contact contact);

}
