package com.codeup.blog.controllers;


import com.codeup.blog.models.User;
import com.codeup.blog.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private UserRepository users;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "users/register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:login";
    }

    @GetMapping("/users/{id}/edit")
    public String showEditUserForm(@PathVariable long id, Model model) {
        model.addAttribute("user", users.findOne(id));
        return "users/edit";
    }

    @PostMapping("/users/{id}/edit")
    public String editUser(@ModelAttribute User userEdited) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = users.findOne(sessionUser.getId());
        userEdited.setId(userDB.getId());
        users.save(userEdited);
        return "redirect:/profile";
    }

    @GetMapping("users/{id}/changePassword")
    public String showPasswordChangeForm(@PathVariable long id ,Model model) {
        model.addAttribute("user", users.findOne(id));
        return "users/changePassword";
    }

    @PostMapping("users/{id}/changePassword")
    public String changePassword(@ModelAttribute User userPasswordChange, @RequestParam("password") String password, @RequestParam("newPassword") String newPassword) {
        User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userDB = users.findOne(sessionUser.getId());
        userPasswordChange.setId(userDB.getId());
//        if (passwordEncoder.matches(password, userPasswordChange.getPassword())) {
//            String hash = passwordEncoder.encode(newPassword);
//            userPasswordChange.setPassword(hash);
//        }
        String hash = passwordEncoder.encode(newPassword);

        userPasswordChange.setPassword(hash);
        users.save(userPasswordChange);

        return "redirect:/profile";
    }
}
