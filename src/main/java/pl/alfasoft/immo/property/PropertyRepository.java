package pl.alfasoft.immo.property;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {

    Page<Property> findPropertiesByAddress_Id(Pageable pageable,  Long addressId);
}
