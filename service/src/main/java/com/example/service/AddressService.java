package com.example.service;

import com.example.entity.Address;

public interface AddressService<T> extends CRUDService<T> {
    Address getAddressIfItExistsOrSave(Address entity);
}
