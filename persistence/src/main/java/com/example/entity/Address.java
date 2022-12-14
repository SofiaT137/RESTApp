package com.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The "Address" class extends "AbstractEntity" class and presents creation of the "Address" entity
 */
@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "addresses")
public class Address extends AbstractEntity<Long>{

    private String city;
    private String street;
    @Column(name = "house_number")
    private Integer houseNumber;
    @Column(name = "office_number")
    private Integer officeNumber;
    private String postcode;
    @OneToMany(mappedBy = "address")
    @JsonManagedReference
    @ToString.Exclude
    @Builder.Default
    private List<User> userList = new ArrayList<>();

    /**
     * Method "deleteAddressFromUser" removes the "Address" entity from the "User" entity
     * @param user User entity
     */
    public void deleteAddressFromUser(User user){
        userList.remove(user);
        user.setAddress(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        if (!super.equals(o)) return false;
        Address address = (Address) o;
        return Objects.equals(getHouseNumber(), address.getHouseNumber())
                && Objects.equals(getOfficeNumber(), address.getOfficeNumber())
                && Objects.equals(getPostcode(), address.getPostcode())
                && Objects.equals(getCity(), address.getCity())
                && Objects.equals(getStreet(), address.getStreet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPostcode(), getCity(),
                getStreet(), getHouseNumber(), getOfficeNumber());
    }
}
