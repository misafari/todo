package com.safari.todo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public record AuthRequestDto(
        @NotBlank
        @JsonProperty("username")
        String username,
        @NotBlank
        @JsonProperty("password")
        String password) {
}
