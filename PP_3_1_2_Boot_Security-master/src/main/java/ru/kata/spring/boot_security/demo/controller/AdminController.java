package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

@Controller
public class AdminController {


    private UserServiceImpl userServiceImpl;

    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, BCryptPasswordEncoder passwordEncoder) {
        this.userServiceImpl = userServiceImpl;
        this.passwordEncoder = passwordEncoder;

    }

    @GetMapping(value = "/")
    public String welcome() {
        return "redirect:/admin";
    }

    @GetMapping("admin")
    public String userList(Model model) {
        model.addAttribute("allUsers", userServiceImpl.getAllUsers());
        return "users";
    }

    @DeleteMapping("admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping(value = "admin/add")
    public String addUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "createUser";
    }

    @PostMapping(value = "admin/add")
    public String addUser(@ModelAttribute("user") User user) {
        userServiceImpl.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "admin/edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id) {
        User user = userServiceImpl.getUserById(id);
        model.addAttribute("user", user);
        return "editUser";
    }

    @PostMapping(value = "admin/edit/{id}")
    public String update(@ModelAttribute("user") User user) {
        userServiceImpl.editUser(user);
        return "redirect:/admin";
    }

    @GetMapping("admin/{userId}")
    public String getUser(@PathVariable("userId") Long userId, Model model) {
        User user1 = (User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        model.addAttribute("OneUser", userServiceImpl.getUserById(userId));
        return "result";
    }

}
