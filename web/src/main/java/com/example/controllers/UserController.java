package com.example.controllers;

import com.example.dto.UserDto;
import com.example.hateoas.Hateoas;
import com.example.service.UserService;
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
@RequestMapping("peopleSystem/v1/users")
public class UserController {

    private UserService<UserDto> userService;
    private final Hateoas<UserDto> userDtoHateoas;

    @Autowired
    public UserController(Hateoas<UserDto> userDtoHateoas) {
        this.userDtoHateoas = userDtoHateoas;
    }

    @Autowired
    @Qualifier("userBusinessService")
    public void setUserService(UserService<UserDto> userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllUsers(@RequestParam(defaultValue = "0",required = false)
                                                      int pageNumber,
                                              @RequestParam (defaultValue = "5", required = false)
                                                      int pageSize){
        Page<UserDto> userDtoPage = userService.getAll(pageNumber,pageSize);
        userDtoPage.forEach(userDtoHateoas::addLinks);
        return new ResponseEntity<>(userDtoPage, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id){
        UserDto userDto = userService.getById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> insertAddress(@RequestBody UserDto userDto){
        userService.insert(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateAddress(@PathVariable Long id, @RequestBody UserDto userDto){
        userService.update(id, userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAddress(@PathVariable Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
