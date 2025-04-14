package com.blankk.spring_api.models;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    @NotBlank
    @Size(min = 3, max = 100)
    private String username;

    @Column(name = "password")
    @NotBlank
    @Size(min = 8, max = 50)
    private String password;
}
