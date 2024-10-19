package com.example.smart_table.user.mapper;

import com.example.smart_table.user.dto.CreateNewUserDto;
import com.example.smart_table.user.dto.UserDto;
import com.example.smart_table.user.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
@Service
public interface UserMapper {

    User toUser(CreateNewUserDto newUserDto);

    UserDto toUserDto(User user);
}