package com.example.service;

import org.springframework.data.domain.Page;

public interface CRUDService<T> {

    void insert(T entity);

    T getById(long id);

    Page<T> getAll(int pageNumber, int pageSize);

    void update(Long id, T entity);

    void delete(Long id);
}
