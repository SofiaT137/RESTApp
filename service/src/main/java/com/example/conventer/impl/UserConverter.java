package com.example.conventer.impl;

import com.example.conventer.Converter;
import com.example.dto.UserDto;
import com.example.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User, UserDto> {

    private static final String PROTECTED_INFO = "Protected info";

    @Override
    public User convert(UserDto value) {
        return User.builder()
                .firstName(value.getFirstName())
                .lastName(value.getLastName())
                .login(value.getLogin())
                .password(value.getPassword())
                .birthday(value.getBirthday())
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
                .build();
    }
}
