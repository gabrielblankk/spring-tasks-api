package com.blankk.spring_api.models;

import com.blankk.spring_api.dtos.TaskCreateDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = Task.TABLE_NAME)
@Table(name = Task.TABLE_NAME)
public class Task {
    public static final String TABLE_NAME = "task";

    public Task (TaskCreateDTO obj) {
        setDueDate(obj.getDueDate());
        setDescription(obj.getDescription());
        setStatus(obj.getStatus());
        setUser(obj.getUser());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @Column(name = "description", nullable = false)
    @NotBlank
    @Size(min = 1, max = 255)
    private String description;

    @Column(name = "due_date", nullable = false)
    @NotBlank
    @Size(min = 10, max = 10)
    private String dueDate;

    @Column(name = "status", nullable = false)
    @NotBlank
    @Size(min = 1, max = 20)
    private String status;
}
