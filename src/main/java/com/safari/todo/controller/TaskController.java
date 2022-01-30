package com.safari.todo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("task")
@PreAuthorize("isAuthenticated()")
public class TaskController {

    @GetMapping("user")
    @Secured("ROLE_ADD_TASK")
    public String helloUser() {
        return "Hello User";
    }
}
