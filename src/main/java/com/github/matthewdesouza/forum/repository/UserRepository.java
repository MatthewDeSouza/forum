package com.github.matthewdesouza.forum.repository;

import com.github.matthewdesouza.forum.entity.Role;
import com.github.matthewdesouza.forum.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    User findUserByUsername(String username);
    Set<User> findUsersByRole(Role role);
}
