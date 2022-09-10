package com.example.service.impl.business_service;

import com.example.conventer.impl.AddressConverter;
import com.example.dto.AddressDto;
import com.example.entity.Address;
import com.example.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service("addressBusinessService")
public class AddressBusinessService implements AddressService<AddressDto> {

    private final AddressConverter addressConverter;
    private AddressService<Address> addressLogicService;

    @Autowired
    public AddressBusinessService(AddressConverter addressConverter) {
        this.addressConverter = addressConverter;
    }

    @Autowired
    @Qualifier("addressLogicService")
    public void setAddressLogicService(AddressService<Address> addressLogicService) {
        this.addressLogicService = addressLogicService;
    }

    @Override
    public void insert(AddressDto entity) {
        addressLogicService.insert(addressConverter.convert(entity));
    }

    @Override
    public AddressDto getById(long id) {
        return addressConverter.convert(addressLogicService.getById(id));
    }

    @Override
    public Page<AddressDto> getAll(int pageNumber, int pageSize) {
        Page<Address> userList = addressLogicService.getAll(pageNumber, pageSize);
        return userList.map(addressConverter::convert);
    }

    @Override
    public void update(Long id, AddressDto entity) {
        Address address = addressConverter.convert(entity);
        addressLogicService.update(id, address);
    }

    @Override
    public void delete(Long id) {
        addressLogicService.delete(id);
    }

    @Override
    public Address getAddressIfItExistsOrSave(Address entity) {
        return addressLogicService.getAddressIfItExistsOrSave(entity);
    }
}
