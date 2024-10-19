package com.example.smart_table.user.service;

import com.example.smart_table.user.dto.CreateNewUserDto;
import com.example.smart_table.user.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto create(CreateNewUserDto newUserDto);

    List<UserDto> getUsers();

    void delete(long userId);
}