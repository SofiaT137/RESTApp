package com.example.conventer.impl;

import com.example.conventer.Converter;
import com.example.dto.AddressDto;
import com.example.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter implements Converter<Address, AddressDto> {

    @Override
    public Address convert(AddressDto value) {
        return Address.builder()
                .city(value.getCity())
                .street(value.getStreet())
                .houseNumber(value.getHouseNumber())
                .officeNumber(value.getOfficeNumber())
                .postcode(value.getPostcode())
                .build();
    }

    @Override
    public AddressDto convert(Address value) {
        return AddressDto.builder()
                .id(value.getId())
                .city(value.getCity())
                .street(value.getStreet())
                .houseNumber(value.getHouseNumber())
                .officeNumber(value.getOfficeNumber())
                .postcode(value.getPostcode())
                .build();
    }
}
