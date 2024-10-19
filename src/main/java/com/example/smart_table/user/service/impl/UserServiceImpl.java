package com.example.smart_table.user.service.impl;

import com.example.smart_table.user.dto.CreateNewUserDto;
import com.example.smart_table.user.dto.UserDto;
import com.example.smart_table.user.mapper.UserMapper;
import com.example.smart_table.user.repository.UserRepository;
import com.example.smart_table.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto create(CreateNewUserDto newUserDto) {
        System.out.println(newUserDto.toString());
        return userMapper.toUserDto(
                userRepository.save(userMapper.toUser(newUserDto))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Пользователь не был найден"));
        userRepository.deleteById(userId);
    }
}