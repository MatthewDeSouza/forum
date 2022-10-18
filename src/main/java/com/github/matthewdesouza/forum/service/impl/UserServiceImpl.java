package com.github.matthewdesouza.forum.service.impl;

import com.github.matthewdesouza.forum.entity.Role;
import com.github.matthewdesouza.forum.entity.User;
import com.github.matthewdesouza.forum.repository.UserRepository;
import com.github.matthewdesouza.forum.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService, LoggingComments {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Queries database to find User by email.
     * @param email email linked to User.
     * @return {@link User}
     */
    @Override
    public User findUserByEmail(String email) {
        infoLogEntityRetrievePending(log, email);
        try {
            User temp = userRepository.findUserByEmail(email);
            infoLogEntityRetrieveSuccess(log, email);
            return temp;
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, email);
            return null;
        }
    }

    /**
     * Queries database to find User by username.
     * @param username username linked to User.
     * @return {@link User}
     */
    @Override
    public User findUserByUsername(String username) {
        infoLogEntityRetrievePending(log, username);
        try {
            User temp = userRepository.findUserByUsername(username);
            infoLogEntityRetrieveSuccess(log, username);
            return temp;
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, username);
            return null;
        }
    }

    /**
     * Queries database to find User by role.
     * @param role role linked to User.
     * @return {@link User}
     */
    @Override
    public Set<User> findUsersByRole(Role role) {
        infoLogEntityRetrievePending(log, role.getId());
        try {
            Set<User> temp = userRepository.findUsersByRole(role);
            infoLogEntityRetrieveSuccess(log, role.getId());
            return temp;
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, role.getId());
            return Collections.emptySet();
        }
    }

    /**
     * Update User entity within the database.
     * @param user Entity to persist.
     */
    @Override
    public void updateUser(User user) {
        infoLogEntityUpdatePending(log, user.getId());
        try {
            User temp = userRepository.getReferenceById(user.getId());
            temp.setEmail(user.getEmail());
            temp.setPosts(user.getPosts());
            temp.setUsername(user.getUsername());
            temp.setRole(user.getRole());
            temp.setCreationDate(user.getCreationDate());
            temp.setPassword(user.getPassword());
            temp.setComments(user.getComments());
            userRepository.save(temp);
            infoLogEntityUpdateSuccess(log, temp.getId());
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, user.getId());
        }
    }

    /**
     * Delete supplied User from the database.
     * @param user User to be updated.
     */
    @Override
    public void deleteUser(User user) {
        infoLogEntityDeletePending(log, user.getId());
        try {
            userRepository.delete(user);
            infoLogEntityDeleteSuccess(log, user.getId());
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, user.getId());
        }
    }

}
