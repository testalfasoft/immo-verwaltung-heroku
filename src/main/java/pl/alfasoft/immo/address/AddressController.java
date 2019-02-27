package pl.alfasoft.immo.address;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.alfasoft.immo.HeaderUtil;
import pl.alfasoft.immo.property.Property;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static pl.alfasoft.immo.HeaderUtil.generatePaginationHttpHeaders;

@CrossOrigin(exposedHeaders = "errors, content-type")
@RestController
@RequestMapping({"/api"})
public class AddressController {

    private final Logger log = LoggerFactory.getLogger(AddressController.class);

    private static final String ENTITY_NAME = "address";

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }


    @GetMapping("/address/add")
    public ResponseEntity<List<Address>> getAllAddresses(Pageable pageable) {
        log.debug("REST request to get a page of Addresses");
        Page<Address> page = addressService.findAll(pageable);
        if (page.isEmpty()) {
            ResponseEntity
                    .status(NOT_FOUND)
                    .build();
        }
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.generatePaginationHttpHeaders(page, "/addresses"))
                .body(page.getContent());
    }


    @GetMapping("/address/add/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable Long id) {

        log.debug("REST request to get Address : {}", id);
        return addressService.findOne(id)
                .map(address1 -> ResponseEntity
                        .ok()
                        .headers(HeaderUtil.createEntityFindOneAlert(ENTITY_NAME, id.toString()))
                        .body(address1)).orElseGet(() -> ResponseEntity
                        .status(NOT_FOUND)
                        .build());
    }


    @PostMapping("/address/add")
    public ResponseEntity<Address> createAddress(@RequestBody Address address) throws URISyntaxException {
        log.debug("REST request to update Address : {}", address);
        if (address == null) {
            return ResponseEntity
                    .status(BAD_REQUEST)
                    .build();
        }
        Address result = addressService.save(address);
        return ResponseEntity
                .created(new URI("/addresses/" + result.getId()))
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, address.getId().toString()))
                .body(result);
    }

    @PostMapping("/address/add/{id}")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address) {
        if (address == null) {
            return ResponseEntity
                    .status(BAD_REQUEST)
                    .build();
        }
        Address result = addressService.save(address);
        return ResponseEntity
                .ok()
                .body(result);
    }

    @DeleteMapping("/address/add/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        log.debug("REST request to delete Address : {}", id);
        if (id == null) {
            return ResponseEntity
                    .status(NOT_FOUND)
                    .build();
        }
        addressService.delete(id);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString()))
                .build();
    }
//    @GetMapping("/address/add/{id}")
//    public ResponseEntity<List<Address>> findAllAddresById(Pageable pageable, @PathVariable Long id) {
//
//        log.debug("REST request to get Property By Address id : {}", id);
//        Page<Address> page = addressService.findAllAddressById(pageable, id);
//        return ResponseEntity
//                .ok()
//                .headers(generatePaginationHttpHeaders(page, "/address/add/{id}"))
//                .body(page.getContent());
//    }
}
