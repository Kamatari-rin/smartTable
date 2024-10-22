package com.example.smart_table.user.service.impl;

import com.example.smart_table.exception.UserNotFoundException;
import com.example.smart_table.user.dto.CreateNewUserDto;
import com.example.smart_table.user.dto.UserDto;
import com.example.smart_table.user.mapper.UserMapper;
import com.example.smart_table.user.repository.UserRepository;
import com.example.smart_table.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Transactional
    public UserDto create(CreateNewUserDto newUserDto) {

        System.out.println(newUserDto.toString());
        return userMapper.toUserDto(
                userRepository.save(userMapper.toUser(newUserDto))
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserDto> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toUserDto);
    }

    @Override
    @Transactional
    public void delete(long userId) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        userRepository.deleteById(userId);
    }
}