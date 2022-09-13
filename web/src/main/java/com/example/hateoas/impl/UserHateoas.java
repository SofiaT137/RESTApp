package com.example.hateoas.impl;

import com.example.controllers.AddressController;
import com.example.controllers.UserController;
import com.example.dto.UserDto;
import com.example.hateoas.Hateoas;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserHateoas implements Hateoas<UserDto> {

    private static final Class<UserController> USER_CONTROLLER = UserController.class;
    private static final Class<AddressController> ADDRESS_CONTROLLER = AddressController.class;

    @Override
    public void addLinks(UserDto entity) {
        entity.add(linkTo(methodOn(USER_CONTROLLER).getUserById(entity.getId())).withSelfRel());
        if(entity.getAddress() != null) {
            entity.getAddress().add(linkTo(methodOn(ADDRESS_CONTROLLER).getAddressById(entity.getAddress()
                    .getId())).withSelfRel());
        }
    }
}
