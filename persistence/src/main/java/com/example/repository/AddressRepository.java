package com.example.repository;

import com.example.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByCityAndStreetAndHouseNumberAndOfficeNumber(String city, String street, Integer houseNumber,
                                                              Integer officeNumber);
}
