package com.example.controllers;

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

    @GetMapping
    public ResponseEntity<Object> getAllUsers(@RequestParam(defaultValue = "0",required = false)
                                                      int pageNumber,
                                              @RequestParam (defaultValue = "5", required = false)
                                                      int pageSize){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PostMapping
//    public ResponseEntity<Object> insertAddress(@RequestBody Address address){
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateAddress(Long id){
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAddress(Long id){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
