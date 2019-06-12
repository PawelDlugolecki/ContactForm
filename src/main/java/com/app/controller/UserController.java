package com.app.controller;

import com.app.model.User;
import com.app.service.UserService;
import com.app.validators.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserValidation userValidation;


    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(userValidation);
    }

    @GetMapping("/add")
    public String addUserGET(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("errors", new HashMap<>());
        return "user/add";
    }

    @PostMapping("/add")
    public String addUserPOST(@Valid @ModelAttribute User user,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getCode));

            model.addAttribute("user", user);
            model.addAttribute("errors", errors);
            return "user/add";
        }
        userService.addUser(user);
        return "redirect:/user";
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("user", userService.getAllUsers());
        return "user/all";
    }

    @GetMapping("/{id}")
    public String getOneUser(@PathVariable Long id, Model model) {
        model.addAttribute("candidate", userService.getOneUser(id));
        return "user/one";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/user";
    }
}