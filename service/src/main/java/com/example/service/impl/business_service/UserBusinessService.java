package com.example.service.impl.business_service;

import com.example.conventer.impl.UserConverter;
import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service("userBusinessService")
public class UserBusinessService implements UserService<UserDto> {

    private final UserConverter userConverter;
    private UserService<User> userLogicService;

    @Autowired
    public UserBusinessService(UserConverter userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public void insert(UserDto entity) {
        userLogicService.insert(userConverter.convert(entity));
    }

    @Override
    public UserDto getById(long id) {
        return userConverter.convert(userLogicService.getById(id));
    }

    @Override
    public Page<UserDto> getAll(int pageNumber, int pageSize) {
        Page<User> userList = userLogicService.getAll(pageNumber, pageSize);
        return userList.map(userConverter::convert);
    }

    @Override
    public void update(Long id, UserDto entity) {
        User user = userConverter.convert(entity);
        userLogicService.update(id,user);
    }

    @Override
    public void delete(Long id) {
        userLogicService.delete(id);
    }
}

