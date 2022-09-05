package com.example.dto;

import com.example.entity.User;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class AddressDto extends RepresentationModel<AddressDto> {

    private String city;
    private String street;
    private Integer houseNumber;
    private Integer officeNumber;
    private String postcode;
    private List<User> userList;

}
