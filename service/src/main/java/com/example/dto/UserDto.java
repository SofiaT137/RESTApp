package com.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class UserDto extends RepresentationModel<UserDto> {

    @Builder.Default
    private Long id = 0L;
    private String firstName;
    private String lastName;
    private String login;
    private char[] password;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime birthday;
    private AddressDto address;
}
