package com.blankk.spring_api.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = Task.TABLE_NAME)
@Table(name = Task.TABLE_NAME)
public class Task {
    public static final String TABLE_NAME = "task";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotBlank
    private User user;

    @Column(name = "description")
    @NotBlank
    @Size(min = 1, max = 255)
    private String description;

    @Column(name = "due_date")
    @NotBlank
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dueDate;

    @Column(name = "status")
    @NotBlank
    @Size(min = 1, max = 20)
    private String status;
}
