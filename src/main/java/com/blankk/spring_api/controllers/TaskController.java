package com.blankk.spring_api.controllers;

import com.blankk.spring_api.dtos.TaskCreateDTO;
import com.blankk.spring_api.dtos.TaskUpdateDTO;
import com.blankk.spring_api.models.Task;
import com.blankk.spring_api.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<Task> getById(@PathVariable Long id) {
        Task obj = taskService.getById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Task>> getByUser(@PathVariable Long id) {
        List<Task> obj = taskService.getByUserId(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody TaskCreateDTO obj) {
        Task task = new Task(obj);
        taskService.create(task);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(task.getId()).toUri();
        return  ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody TaskUpdateDTO obj) {
        taskService.update(id, obj);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
