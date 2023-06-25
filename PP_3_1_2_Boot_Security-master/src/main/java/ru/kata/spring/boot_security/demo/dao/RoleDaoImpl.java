package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    public RoleDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role findById(Long role_id) {
        return entityManager.find(Role.class, role_id);
    }

    public List<Role> getAllRoles() {
        return entityManager.createQuery("SELECT r FROM Role r").getResultList();
    }

    public void addRole(Role role) {
        entityManager.persist(role);

    }

    public Role getRole(String userRole) {
        return entityManager.createQuery("SELECT r FROM Role r WHERE r.userRole =: userRole", Role.class)
                .setParameter("userRole", userRole).getSingleResult();
    }
}
