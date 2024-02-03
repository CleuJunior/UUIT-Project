package com.interion.uuit.services;

import com.interion.uuit.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CrudService<T> {

    T insert(T request);

    void update(String id, T request);

    T findById(String id) throws NotFoundException;

    List<T> findAll();

    Page<T> findAll(Pageable page);

    void delete(String id) throws NotFoundException;

}
