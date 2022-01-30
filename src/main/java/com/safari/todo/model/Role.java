package com.safari.todo.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String main;
    private String category;
    private String name;

    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles;
}
