package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;

/**
 * The abstract class "AbstractEntity" presents creation of the abstract entity
 */
@MappedSuperclass
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractEntity <K extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private K id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity<?> that = (AbstractEntity<?>) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
