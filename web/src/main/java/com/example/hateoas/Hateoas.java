package com.example.hateoas;

import org.springframework.hateoas.RepresentationModel;

public interface Hateoas <T extends RepresentationModel<T>>  {

    /**
     * Method addLinks allows adding links to the RepresentationModel entity
     * @param entity RepresentationModel entity
     */
    void addLinks(T entity);
}