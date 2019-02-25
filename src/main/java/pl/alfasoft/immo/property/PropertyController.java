package pl.alfasoft.immo.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.alfasoft.immo.BadRequestAlertException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static pl.alfasoft.immo.HeaderUtil.*;
@RequestMapping({"/api"})
@Controller
public class PropertyController {

    private final Logger log = LoggerFactory.getLogger(PropertyController.class);

    private static final String ENTITY_NAME = "property";

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping("/properties")
    public ResponseEntity<List<Property>> getAllProperties(Pageable pageable) {

        log.debug("REST request to get a page of Properties");
        Page<Property> page = propertyService.findAll(pageable);
        return ResponseEntity
                .ok()
                .headers(generatePaginationHttpHeaders(page, "/properties"))
                .body(page.getContent());
    }

    @GetMapping("/properties/{id}")
    public ResponseEntity<Property> getProperty(@PathVariable Long id) {

        log.debug("REST request to get Property : {}", id);
        Optional<Property> property = propertyService.findOne(id);
        if (!property.isPresent()) {
            throw new BadRequestAlertException("Invalid id" + ENTITY_NAME + "idnull");
        } else {
            return ResponseEntity
                    .ok()
                    .headers(createEntityFindOneAlert(ENTITY_NAME, id.toString()))
                    .body(property.get());
        }
    }

    @PostMapping("/properties")
    public ResponseEntity<Property> createProperty(@RequestBody Property property) throws URISyntaxException {

        log.debug("REST request to save Property : {}", property);

        if (property.getId() != null) {
            throw new BadRequestAlertException("A new property cannot already have an ID" + ENTITY_NAME + "idexists");
        }

        Property result = propertyService.save(property);
        return ResponseEntity
                .created(new URI("/properties/" + result.getId()))
                .headers(createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    @PutMapping("/properties")
    public ResponseEntity<Property> updateProperty(@RequestBody Property property) {

        log.debug("REST request to update Property : {}", property);

        if (property.getId() == null) {
            throw new BadRequestAlertException("Invalid id" + ENTITY_NAME + "idnull");
        }

        Property result = propertyService.save(property);
        return ResponseEntity
                .ok()
                .headers(createEntityUpdateAlert(ENTITY_NAME, property.getId().toString()))
                .body(result);
    }

    @DeleteMapping("/properties/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id) {

        log.debug("REST request to delete Property : {}", id);
        propertyService.delete(id);
        return ResponseEntity
                .ok()
                .headers(createEntityDeletionAlert(ENTITY_NAME, id.toString()))
                .build();
    }


}
