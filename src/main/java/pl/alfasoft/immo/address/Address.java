package pl.alfasoft.immo.address;

import pl.alfasoft.immo.property.Property;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "house", schema = "public")
public class Address {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "h_id")
    private Long id;

    @Column(name = "h_street")
    private String street;

    @Column(name = "h_postalCode")
    private String postal_code;

    @Column(name = "h_postalCity")
    private String city;

    @Column(name = "h_numberOfproperties")
    private Integer numberOfProperties;

    @OneToMany(mappedBy = "address", cascade = ALL)
    private List<Property> properties = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public Integer getNumberOfProperties() {
        return numberOfProperties;
    }

    public void setNumberOfProperties(Integer numberOfProperties) {
        this.numberOfProperties = numberOfProperties;
    }
}
