package com.example.service.impl.logic_service;

import com.example.entity.Address;
import com.example.repository.AddressRepository;
import com.example.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service("addressLogicService")
public class AddressLogicService implements AddressService<Address> {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressLogicService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    @Transactional
    public void insert(Address entity) {
        if(findAddressByItsCityStreetHouseNumberOfficeNumber(entity).isPresent()) {
            throw new RuntimeException("This address exists!");
        }else{
            addressRepository.save(entity);
        }
    }

    @Override
    public Address getById(long id) {
        Optional<Address> address =  addressRepository.findById(id);
        if (!address.isPresent()){
            throw new RuntimeException("Cannot find this address by its id!");
        }
        return address.get();
    }

    @Override
    public Page<Address> getAll(int pageNumber, int pageSize) {
        return addressRepository.findAll(PageRequest.of(pageNumber,pageSize));
    }

    @Override
    @Transactional
    public void update(Long id, Address entity) {

        Address address = getById(id);

        if (entity.getCity() != null) {
            address.setCity(entity.getCity());
        }
        if (entity.getStreet() != null) {
            address.setStreet(entity.getStreet());
        }
        if (entity.getHouseNumber() != null){
            address.setHouseNumber(entity.getHouseNumber());
        }
        if (entity.getOfficeNumber() != null) {
            address.setOfficeNumber(entity.getOfficeNumber());
        }
        if (entity.getPostcode() != null) {
            address.setPostcode(entity.getPostcode());
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Address address = getById(id);
        addressRepository.delete(address);
    }

    @Transactional
    public Address getAddressIfItExistsOrSave(Address entity){
        if (!findAddressByItsCityStreetHouseNumberOfficeNumber(entity).isPresent()){
            addressRepository.save(entity);
        }
       Optional<Address> address = findAddressByItsCityStreetHouseNumberOfficeNumber(entity);
        if (!address.isPresent()){
            throw new RuntimeException("Cannot find this address!");
        }
        return address.get();
    }

    private Optional<Address> findAddressByItsCityStreetHouseNumberOfficeNumber(Address entity){
        return addressRepository.findByCityAndStreetAndHouseNumberAndOfficeNumber(entity.getCity(),
                entity.getStreet(),entity.getHouseNumber(),entity.getOfficeNumber());
    }


}
