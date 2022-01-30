package com.safari.todo.config;

import com.safari.todo.model.Role;
import com.safari.todo.model.User;
import com.safari.todo.model.UserRole;
import com.safari.todo.repository.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommandLineRunnerImpl implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    @Override
    public void run(String... args) throws Exception {
        var user = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("123"))
                .deleted(false)
                .build();

        var role = Role.builder()
                .main("task")
                .category("modify")
                .name("ADD_TASK")
                .build();

        var userRole = UserRole.builder()
                .user(user)
                .role(role)
                .build();

        userRoleRepository.save(userRole);
    }
}
