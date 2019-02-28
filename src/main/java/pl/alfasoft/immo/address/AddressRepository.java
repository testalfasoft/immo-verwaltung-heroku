package pl.alfasoft.immo.address;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.alfasoft.immo.property.Property;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query(value = "SELECT name FROM Property ORDER BY name DESC")
    Page<Address> findAllByAddressByAddress_Id(Pageable pageable, Long id);
}
