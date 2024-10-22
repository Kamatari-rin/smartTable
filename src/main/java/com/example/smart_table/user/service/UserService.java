package com.example.smart_table.user.service;

import com.example.smart_table.user.dto.CreateNewUserDto;
import com.example.smart_table.user.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserDto create(CreateNewUserDto newUserDto);

    Page<UserDto> getUsers(Pageable pageable);

    void delete(long userId);
}