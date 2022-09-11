package com.example.controllers;

import com.example.dto.AddressDto;
import com.example.hateoas.Hateoas;
import com.example.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("peopleSystem/v1/address")
public class AddressController {

    private AddressService<AddressDto> addressService;
    private final Hateoas<AddressDto> addressDtoHateoas;

    @Autowired
    public AddressController(Hateoas<AddressDto> addressDtoHateoas) {
        this.addressDtoHateoas = addressDtoHateoas;
    }

    @Autowired
    @Qualifier("addressBusinessService")
    public void setAddressService(AddressService<AddressDto> addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllAddresses(@RequestParam(defaultValue = "0",required = false)
                                                              int pageNumber,
                                                  @RequestParam (defaultValue = "5", required = false)
                                                              int pageSize){
        Page<AddressDto> userDtoList = addressService.getAll(pageNumber,pageSize);
        userDtoList.forEach(addressDtoHateoas::addLinks);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAddressById(@PathVariable Long id){
        AddressDto addressDto = addressService.getById(id);
        addressDtoHateoas.addLinks(addressDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> insertAddress(@RequestBody AddressDto address){
        addressService.insert(address);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateAddress(@PathVariable Long id,
                                                @RequestBody AddressDto address){
        addressService.update(id,address);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAddress(Long id){
        addressService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
