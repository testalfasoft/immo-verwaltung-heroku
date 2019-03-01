package pl.alfasoft.immo.address;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.alfasoft.immo.property.Property;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

//    Page<Address> findAllByAddressByAddress_Id(Pageable pageable, Long id);
//    @Query(value = "SELECT id FROM Address ORDER BY id ASC")
    Page<Address> findAllByAddressByAddress_Id(Sort id);
}
