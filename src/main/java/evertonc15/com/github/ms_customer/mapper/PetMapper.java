package evertonc15.com.github.ms_customer.mapper;

import evertonc15.com.github.ms_customer.domain.Pet;
import evertonc15.com.github.ms_customer.dto.PetDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.Set;

@Mapper(componentModel = "spring")
@Component
public interface PetMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customers", ignore = true)
    Set<Pet> dtoToEntity(Set<PetDTO> petsDTO);

    Set<PetDTO> entityToDto(Set<Pet> pets);

}
