package evertonc15.com.github.ms_customer.mapper;

import evertonc15.com.github.ms_customer.domain.Contact;
import evertonc15.com.github.ms_customer.dto.ContactDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-01T22:56:10-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class ContactMapperImpl implements ContactMapper {

    @Override
    public Contact dtoToEntity(ContactDTO contactsDTO) {
        if ( contactsDTO == null ) {
            return null;
        }

        Contact contact = new Contact();

        contact.setMobileNumber( contactsDTO.getMobileNumber() );
        contact.setPhone( contactsDTO.getPhone() );

        return contact;
    }

    @Override
    public ContactDTO entityToDto(Contact contact) {
        if ( contact == null ) {
            return null;
        }

        ContactDTO.ContactDTOBuilder contactDTO = ContactDTO.builder();

        contactDTO.mobileNumber( contact.getMobileNumber() );
        contactDTO.phone( contact.getPhone() );

        return contactDTO.build();
    }
}
