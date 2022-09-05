package com.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@Table(name = "users")
@NoArgsConstructor
public class User extends AbstractEntity<Long> {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String login;
    private char[] password;
    private LocalDateTime birthday;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "address_id")
    @ToString.Exclude
    private Address address;

    public void addUserToAddress(Address address){
        setAddress(address);
        address.getUserList().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(getLogin(), user.getLogin());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getLogin());
    }
}
