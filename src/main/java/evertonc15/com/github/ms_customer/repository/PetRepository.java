package evertonc15.com.github.ms_customer.repository;

import evertonc15.com.github.ms_customer.domain.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;


public interface PetRepository extends JpaRepository<Pet, Long> {

    @Query("""
        select case when count(c) > 0 then true else false end
                from Pet p join p.customers c
                        where c.id = :id and p.name in :petsName
        """)
    boolean existsPetFromCustomer(@Param("id") Long id, 
                                  @Param("petsName") Set<String> petsName);
}
