package com.hb.system.ecommerce.shoes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hb.system.ecommerce.shoes.entity.User;
import com.hb.system.ecommerce.shoes.services.UserService;

@Controller
// @RestController
// @Controller
@RequestMapping("/user")

public class UserController {
     @Autowired
    private UserService userService;

    @GetMapping({"/list"})
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("contenido", "users/UserList");
        return "layout/index";
    }

    @GetMapping({"/create"})
    public String showCreateForm(User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("contenido", "users/UserCreate");
        return "layout/index";
    }

    @PostMapping({"/store"})
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/user/list";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        model.addAttribute("contenido", "users/UserEdit");
        return "layout/index";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteConfirm(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "confirm-delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    
}
