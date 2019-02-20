package pl.alfasoft.immo.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PropertyService {


    private final PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Property save(Property property) {
        return propertyRepository.save(property);
    }

    @Transactional(readOnly = true)
    public Sort findAll(Pageable pageable) {
        return propertyRepository.findAll(pageable).getSort().descending();
    }


    @Transactional(readOnly = true)
    public Optional<Property> findOne(Long id) {
        return propertyRepository.findById(id);
    }

    public void delete(Long id) {
        propertyRepository.deleteById(id);
    }

    public Page<Property> findAllByAddressId(Pageable pageable, Long addressId) {
        return propertyRepository.findAllByPropertiesByAddress_Id(pageable, addressId);
    }
}
