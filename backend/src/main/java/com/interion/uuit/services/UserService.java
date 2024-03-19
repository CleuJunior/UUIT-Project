package com.interion.uuit.services;

import com.interion.uuit.entities.User;
import com.interion.uuit.exceptions.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class UserService<T extends Serializable> implements CrudService<User<T>> {

    @Override
    public User<?> insert(User request) {
        return null;
    }

    @Override
    public void update(String id, User request) {

    }

    @Override
    public User findById(String id) throws NotFoundException {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Page<User> findAll(Pageable page) {
        return null;
    }

    @Override
    public void delete(String id) throws NotFoundException {

    }
}
