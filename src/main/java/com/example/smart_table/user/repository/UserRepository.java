package com.example.smart_table.user.repository;

import com.example.smart_table.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}