package com.example.repository;

import com.example.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The "AddressRepository" interface extends JpaRepository functionality for the Address entity
 */
public interface AddressRepository extends JpaRepository<Address, Long> {

    /**
     * Method "findByCityAndStreetAndHouseNumberAndOfficeNumber" searches the "Address" entity by its city,
     * street, houseNumber, officeNumber
     * @param city String city
     * @param street String street
     * @param houseNumber Integer houseNumber
     * @param officeNumber Integer officeNumber
     * @return Optional of the "Address" entity
     */
    Optional<Address> findByCityAndStreetAndHouseNumberAndOfficeNumber(String city, String street, Integer houseNumber,
                                                              Integer officeNumber);
}
