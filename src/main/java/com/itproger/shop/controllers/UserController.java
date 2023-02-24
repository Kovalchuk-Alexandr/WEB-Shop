package com.itproger.shop.controllers;

//import ch.qos.logback.core.model.Model;
import com.itproger.shop.models.Role;
import com.itproger.shop.models.User;
import com.itproger.shop.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // кеш-пароль
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/reg")
    public String reg(@RequestParam(name = "error", defaultValue = "", required = false) String error, Model model) {
        if(error.equals("username")){
            model.addAttribute("error", "Такой логин уже занят");
        }
        return "reg";
    }

    @PostMapping("/reg")
    public String addUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String email ) {
        // Проверяем, есть ли уже такой пользователь
        if(userRepository.findByUsername(username) != null) {
            return "redirect:/reg?error=username";
        }
        password = passwordEncoder.encode(password);    //создание кеш-пароля
        User user = new User(username, password, email, true, Collections.singleton(Role.ADMIN));
        userRepository.save(user);
        return "redirect:/login";
    }

}
