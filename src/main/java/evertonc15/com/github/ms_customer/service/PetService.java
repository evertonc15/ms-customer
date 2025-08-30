package evertonc15.com.github.ms_customer.service;

import evertonc15.com.github.ms_customer.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    public boolean existsPetFromCustomer(Long id, Set<String> petNames) {
        return petRepository.existsPetFromCustomer(id, petNames);
    }
}
