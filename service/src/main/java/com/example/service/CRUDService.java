package com.example.service;

import org.springframework.data.domain.Page;

public interface CRUDService<T> {

    T insert(T entity);

    T getById(long id);

    Page<T> getAll(int pageSize, int pageNumber);

    T update(Long id, T entity);

    void delete(long id);
}
