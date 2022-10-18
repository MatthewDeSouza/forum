package com.github.matthewdesouza.forum.service;

import com.github.matthewdesouza.forum.entity.Role;
import com.github.matthewdesouza.forum.entity.User;

import java.util.Set;

public interface UserService {
    User findUserByEmail(String email);
    User findUserByUsername(String username);
    Set<User> findUsersByRole(Role role);
    void updateUser(User user);
    void deleteUser(User user);
}
