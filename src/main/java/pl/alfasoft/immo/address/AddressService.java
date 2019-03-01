package pl.alfasoft.immo.address;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.alfasoft.immo.property.Property;
import pl.alfasoft.immo.property.PropertyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AddressService {

    private final Logger log = LoggerFactory.getLogger(AddressService.class);

    private final AddressRepository addressRepository;
    private final PropertyRepository propertyRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository, PropertyRepository propertyRepository) {
        this.addressRepository = addressRepository;
        this.propertyRepository = propertyRepository;
    }

    public Address save(Address address) {
        log.debug("Request to save Address : {}", address);
        List<Property> listOfProperties = new ArrayList<>();
        for (int i = 0; i < address.getNumberOfProperties(); i++) {
            Property property = new Property();
            property.setName("Wohnung " + (i + 1));
            property.setAddress(address);
            listOfProperties.add(property);
            // propertyRepository.save(property); //FIXME 07.02
        }
        address.setProperties(listOfProperties);
        return addressRepository.save(address);
    }

    @Transactional(readOnly = true)
    public Page<Address> findAll(Pageable pageable) {

        log.debug("Request to get all Addresses");
        return addressRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Address> findOne(Long id) {

        log.debug("Request to get Address : {}", id);
        return addressRepository.findById(id);
    }

    public void delete(Long id) {

        log.debug("Request to delete Address : {}", id);
        addressRepository.deleteById(id);
    }

    public Page<Address> findAllById(Long id) {
        return addressRepository.findAllById(id);
    }
}
