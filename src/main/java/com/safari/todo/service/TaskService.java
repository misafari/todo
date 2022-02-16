package com.safari.todo.service;

import com.safari.todo.model.Task;
import com.safari.todo.model.User;
import com.safari.todo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository repository;
    private final UserService userService;

    @Transactional
    public void persist(String taskName) throws UsernameNotFoundException {
        var task = new Task(taskName);
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var user = userService.findUserByUsername(username);

        task.setOwner(user);

        repository.save(task);
    }

    public List<Task> findAllByOwner() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        var tasks = repository.findByOwner_Username(username);

        System.out.println(tasks);

        return tasks;
    }
}
