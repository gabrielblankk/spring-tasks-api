package com.blankk.spring_api.models;

import com.blankk.spring_api.dtos.UserCreateDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = User.TABLE_NAME)
@Table(name = User.TABLE_NAME)
public class User {
    public static final String TABLE_NAME = "user";

    public User(UserCreateDTO obj) {
        setUsername(obj.getUsername());
        setPassword(obj.getPassword());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    @NotBlank
    @Size(min = 3, max = 100)
    private String username;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 8, max = 50)
    private String password;
}
