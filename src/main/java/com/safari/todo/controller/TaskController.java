package com.safari.todo.controller;

import com.safari.todo.dto.TaskResponseDto;
import com.safari.todo.model.Task;
import com.safari.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("task")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService service;


    @PostMapping
    @Secured("ROLE_ADD_TASK")
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody Map<String,Object> body) {
        var taskName = body.get("name").toString();

        if (taskName == null || taskName.isBlank()) {
            // todo throw exception
            return;
        }

        service.persist(taskName);
    }

    @GetMapping("user")
    @Secured("ROLE_ADD_TASK")
    @ResponseBody
    public List<TaskResponseDto> getMyTasks() {
        return service.findAllByOwner().stream().map(TaskResponseDto::new).collect(Collectors.toList());
    }
}
