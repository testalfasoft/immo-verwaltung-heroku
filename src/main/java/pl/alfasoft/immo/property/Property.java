package pl.alfasoft.immo.property;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.alfasoft.immo.address.Address;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "flat", schema = "public")
public class Property {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "fl_id")
    private Long id;

    @Column(name = "fl_floor")
    private String floor;

    @Column(name = "fl_name")
    private String name;

    @ManyToOne
    @JsonIgnoreProperties("properties")
    @JoinColumn(name = "fl_h_id")
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
