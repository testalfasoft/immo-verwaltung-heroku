package pl.alfasoft.immo.property;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    @Query(value = "SELECT id FROM Property ORDER BY id ASC")
    Page<Property> findAllByPropertiesByAddress_Id(Pageable pageable, Long addressId);
}
