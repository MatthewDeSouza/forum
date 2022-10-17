package com.github.matthewdesouza.forum.repository;

import com.github.matthewdesouza.forum.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findRoleByName(String name);
    Role findRoleById(Long id);
    void deleteRoleByName(String name);
}
