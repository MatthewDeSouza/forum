package com.github.matthewdesouza.forum.service.impl;

import com.github.matthewdesouza.forum.entity.Role;
import com.github.matthewdesouza.forum.repository.RoleRepository;
import com.github.matthewdesouza.forum.service.RoleService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
public class RoleServiceImpl implements RoleService, LoggingComments {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Retrieve Role by id from the database.
     * Returns null if not found.
     * @param id The id of the Role.
     * @return {@link Role}
     */
    @Override
    public Role getRoleById(@NonNull Long id) {
        infoLogEntityRetrievePending(log, id);
        try {
            Role temp = roleRepository.findRoleById(id);
            infoLogEntityRetrieveSuccess(log, id);
            return temp;
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, id);
            return null;
        }
    }

    /**
     * Retrieve Role by name from the database.
     * Returns null if not found.
     * @param name The name of the Role.
     * @return {@link Role}
     */
    @Override
    public Role getRoleByName(@NonNull String name) {
        infoLogEntityRetrievePending(log, name);
        try {
            Role temp = roleRepository.findRoleByName(name);
            infoLogEntityRetrieveSuccess(log, name);
            return temp;
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, name);
            return null;
        }
    }

    /**
     * Save a role to the database.
     * @param role The Role to save.
     */
    @Override
    public void saveRole(Role role) {
        infoLogEntitySavePending(log, role.getId());
        try {
            roleRepository.save(role);
            infoLogEntitySaveSuccess(log, role.getId());
        } catch (EntityNotFoundException e) {
            errorLogEntityAlreadyExists(log, role.getId());
        }
    }

    /**
     * Update a role within the database.
     * @param role The Role to update.
     */
    @Override
    public void updateRole(Role role) {
        infoLogEntityUpdatePending(log, role.getId());
        try {
            Role tempRole = roleRepository.getReferenceById(role.getId());
            tempRole.setName(role.getName());
            tempRole.setUsers(role.getUsers());
            roleRepository.save(tempRole);
            infoLogEntityUpdateSuccess(log, role.getId());
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, role.getId());
        }
    }

    /**
     * Remove a role from the database.
     * @param role The Role to remove.
     */
    @Override
    public void deleteRole(Role role) {
        infoLogEntityDeletePending(log, role.getId());
        try {
            roleRepository.delete(role);
            infoLogEntityDeleteSuccess(log, role.getId());
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, role.getId());
        }
    }

    /**
     * Remove a role from the database by primary key.
     * @param id The primary key of the Role.
     */
    @Override
    public void deleteRoleById(@NonNull Long id) {
        infoLogEntityDeletePending(log, id);
        try {
            roleRepository.deleteById(id);
            infoLogEntityDeleteSuccess(log, id);
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, id);
        }
    }

    /**
     * Remove a role from the database by name.
     * @param name The name of the Role.
     */
    @Override
    public void deleteRoleByName(@NonNull String name) {
        infoLogEntityDeletePending(log, name);
        try {
            roleRepository.deleteRoleByName(name);
            infoLogEntityDeleteSuccess(log, name);
        } catch (EntityNotFoundException e) {
            errorLogEntityNotFound(log, name);
        }
    }
}
