package com.test.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "addresses")
public class Address extends AbstractEntity<Long>{

    private String country;
    private String city;
    private String street;
    @Column(name = "house_number")
    private int houseNumber;
    @Column(name = "office_number")
    private int officeNumber;
    private int postcode;
    @OneToMany(mappedBy = "address")
    @JsonManagedReference
    @ToString.Exclude
    private List<User> userList = new ArrayList<>();

    public void addAddressToUser(User user){
        userList.add(user);
        user.setAddress(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        if (!super.equals(o)) return false;
        Address address = (Address) o;
        return getHouseNumber() == address.getHouseNumber()
                && getOfficeNumber() == address.getOfficeNumber()
                && getPostcode() == address.getPostcode()
                && Objects.equals(getCountry(), address.getCountry())
                && Objects.equals(getCity(), address.getCity())
                && Objects.equals(getStreet(), address.getStreet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCountry(), getCity(),
                getStreet(), getHouseNumber(), getOfficeNumber(), getPostcode());
    }
}
