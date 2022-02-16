package com.safari.todo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.safari.todo.model.Task;

import java.time.LocalDateTime;

public record TaskResponseDto(
        String name,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        LocalDateTime createAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
        LocalDateTime updateAt,
        boolean done
) {
    public TaskResponseDto(Task task) {
        this(task.getName(), task.getCreatedAt(), task.getUpdatedAt(), task.isDone());
    }
}
