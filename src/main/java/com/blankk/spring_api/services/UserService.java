package com.blankk.spring_api.services;

import com.blankk.spring_api.models.User;
import com.blankk.spring_api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getById(Long id) {
        Optional<User> user = this.userRepository.findById(id);

        return user.orElseThrow(() -> new RuntimeException("Usuário não encontrado: Id:" + id));
    }

    @Transactional
    public User create(User obj) {
        obj.setId(null);
        obj = this.userRepository.save(obj);
        return obj;
    }

    @Transactional
    public User changeUsername(Long id, String newUsername) {
        if (userRepository.existsByUsername(newUsername)) {
            throw new RuntimeException("Um usuário com esse nome já existe.");
        }

        User user = getById(id);
        user.setUsername(newUsername);
        return userRepository.save(user);
    }

    @Transactional
    public User changePassword(Long id, String newPassword) {
        User user = getById(id);
        user.setPassword(newPassword);
        return userRepository.save(user);
    }

    public void delete(Long id) {
        getById(id);

        userRepository.deleteById(id);
    }
}
