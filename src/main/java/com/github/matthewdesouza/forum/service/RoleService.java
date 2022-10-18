package com.github.matthewdesouza.forum.service;

import com.github.matthewdesouza.forum.entity.Role;
import lombok.NonNull;

public interface RoleService {
    Role getRoleById(@NonNull Long id);
    Role getRoleByName(@NonNull String name);
    void saveRole(Role role);
    void updateRole(Role role);
    void deleteRole(Role role);
    void deleteRoleById(@NonNull Long id);
    void deleteRoleByName(@NonNull String name);
}
