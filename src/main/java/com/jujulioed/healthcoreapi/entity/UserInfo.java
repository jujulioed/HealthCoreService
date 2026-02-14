package com.jujulioed.healthcoreapi.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@Table(name="users")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name="roles")
    private String roles;

    @Column(name="password")
    private String password;

    public UserInfo(String name, String email, String roles, String password) {
        this.name = name;
        this.email = email;
        this.roles = roles;
        this.password = password;
    }
}
