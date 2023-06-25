package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


public interface UserService {
    public void saveUser(User user);

    public void editUser(User user);

    public void deleteUser(Long id);

    List<User> getAllUsers();

    User getByUsername(String login);

    User getUserById(Long id);

}
