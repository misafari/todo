package com.safari.todo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public record AuthResponseDto(
        @JsonProperty("username")
        String toke) {
}
