package com.example.conventer.impl;

import com.example.conventer.Converter;
import com.example.dto.AddressDto;
import com.example.dto.UserDto;
import com.example.entity.Address;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User, UserDto> {

    private static final String PROTECTED_INFO = "Protected info";

    private final Converter<Address, AddressDto> addressConverter;

    @Autowired
    public UserConverter(Converter<Address, AddressDto> addressConverter) {
        this.addressConverter = addressConverter;
    }


    @Override
    public User convert(UserDto value) {
        return User.builder()
                .firstName(value.getFirstName())
                .lastName(value.getLastName())
                .login(value.getLogin())
                .password(value.getPassword())
                .birthday(value.getBirthday())
                .address(value.getAddress() == null ? null : addressConverter.convert(value.getAddress()))
                .build();
    }

    @Override
    public UserDto convert(User value) {
        return UserDto.builder()
                .id(value.getId())
                .firstName(value.getFirstName())
                .lastName(value.getLastName())
                .login(value.getLogin())
                .password(PROTECTED_INFO.toCharArray())
                .birthday(value.getBirthday())
                .address(addressConverter.convert(value.getAddress()))
                .build();
    }
}
