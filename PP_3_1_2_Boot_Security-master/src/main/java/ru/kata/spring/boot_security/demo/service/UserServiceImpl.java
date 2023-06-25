package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDaoImpl;
import ru.kata.spring.boot_security.demo.model.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @PersistenceContext
    private EntityManager entityManager;

    UserDaoImpl userDao;

    BCryptPasswordEncoder PasswordEncoder;


    public UserServiceImpl(UserDaoImpl userDao, BCryptPasswordEncoder PasswordEncoder) {
        this.userDao = userDao;
        this.PasswordEncoder = PasswordEncoder;
    }

    @Override
    public User getByUsername(String username) {

        return userDao.getUserByName(username);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Transactional
    public void saveUser(User user) {
        user.setUsername(user.getUsername());
        user.setPassword(PasswordEncoder.encode(user.getPassword()));
        userDao.saveUser(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }


    @Transactional
    public void editUser(User user) {
        if (!user.getPassword().equals(userDao.getUserById(user.getId()).getPassword())) {
            user.setPassword(PasswordEncoder.encode(user.getPassword()));
        }
        userDao.updateUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь не найден" + " " + username);
        }
        return user;
    }
}
