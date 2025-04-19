package com.blankk.spring_api.services;

import com.blankk.spring_api.dtos.TaskUpdateDTO;
import com.blankk.spring_api.models.Task;
import com.blankk.spring_api.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task getById(Long id) {
        Optional<Task> task = taskRepository.findById(id);

        return task.orElseThrow(() -> new RuntimeException("Task não encontrada: Id:" + id));
    }

    public List<Task> getByUserId(Long id) {
        userService.getById(id);

        return taskRepository.findByUser_Id(id);
    }

    @Transactional
    public Task create(Task obj) {
        userService.getById(obj.getUser().getId());

        return taskRepository.save(obj);
    }

    @Transactional
    public Task update(Long id, TaskUpdateDTO obj) {
        Task newObj = getById(id);
        newObj.setDescription(obj.getDescription());
        newObj.setStatus(obj.getStatus());
        newObj.setDueDate(obj.getDueDate());

        return taskRepository.save(newObj);
    }

    public void delete(Long id) {
        getById(id);

        taskRepository.deleteById(id);
    }
}
