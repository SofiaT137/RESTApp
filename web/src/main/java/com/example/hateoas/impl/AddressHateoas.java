package com.example.hateoas.impl;

import com.example.controllers.AddressController;
import com.example.dto.AddressDto;
import com.example.hateoas.Hateoas;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AddressHateoas implements Hateoas<AddressDto> {

    private static final Class<AddressController> ADDRESS_CONTROLLER = AddressController.class;

    @Override
    public void addLinks(AddressDto entity) {
        entity.add(linkTo(methodOn(ADDRESS_CONTROLLER).
                getAddressById(entity.getId())).withSelfRel());
    }
}
