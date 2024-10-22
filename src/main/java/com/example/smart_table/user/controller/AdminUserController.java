package com.example.smart_table.user.controller;

import com.example.smart_table.user.dto.CreateNewUserDto;
import com.example.smart_table.user.dto.UserDto;
import com.example.smart_table.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/users")
@Validated
public class AdminUserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> save(@Valid @RequestBody CreateNewUserDto newUser) {
        return new ResponseEntity<>(userService.create(newUser), HttpStatus.CREATED);
    }

    @DeleteMapping("{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long userId) {
        userService.delete(userId);
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> getUsers(
            @PageableDefault(size = 10, page = 0)
            Pageable pageable) {
        Page<UserDto> userPage = userService.getUsers(pageable);
        if (userPage.isEmpty()) return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userPage);
        return new ResponseEntity<>(userService.getUsers(pageable), HttpStatus.OK);
    }
}