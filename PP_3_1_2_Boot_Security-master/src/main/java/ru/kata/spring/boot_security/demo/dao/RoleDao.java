package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleDao {

    Role findById(Long role_id);

    public Role getRole(String userRole);

    public void addRole(Role role);

    public List<Role> getAllRoles();
}
