package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    Role findById(Long role_id);

    List<Role> getAllRoles();

    Role getRole(String userRole);

    public void addRole(Role role);
}
