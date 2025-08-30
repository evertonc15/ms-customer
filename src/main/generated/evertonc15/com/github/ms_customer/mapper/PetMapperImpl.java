package evertonc15.com.github.ms_customer.mapper;

import evertonc15.com.github.ms_customer.domain.Pet;
import evertonc15.com.github.ms_customer.dto.PetDTO;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-29T21:35:08-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 17.0.14 (Amazon.com Inc.)"
)
@Component
public class PetMapperImpl implements PetMapper {

    @Override
    public Set<Pet> dtoToEntity(Set<PetDTO> petsDTO) {
        if ( petsDTO == null ) {
            return null;
        }

        Set<Pet> set = new LinkedHashSet<Pet>( Math.max( (int) ( petsDTO.size() / .75f ) + 1, 16 ) );
        for ( PetDTO petDTO : petsDTO ) {
            set.add( petDTOToPet( petDTO ) );
        }

        return set;
    }

    @Override
    public Set<PetDTO> entityToDto(Set<Pet> pets) {
        if ( pets == null ) {
            return null;
        }

        Set<PetDTO> set = new LinkedHashSet<PetDTO>( Math.max( (int) ( pets.size() / .75f ) + 1, 16 ) );
        for ( Pet pet : pets ) {
            set.add( petToPetDTO( pet ) );
        }

        return set;
    }

    protected Pet petDTOToPet(PetDTO petDTO) {
        if ( petDTO == null ) {
            return null;
        }

        Pet.PetBuilder pet = Pet.builder();

        pet.name( petDTO.getName() );

        return pet.build();
    }

    protected PetDTO petToPetDTO(Pet pet) {
        if ( pet == null ) {
            return null;
        }

        PetDTO.PetDTOBuilder petDTO = PetDTO.builder();

        petDTO.name( pet.getName() );

        return petDTO.build();
    }
}
