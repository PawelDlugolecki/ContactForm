package com.app.service;

import com.app.exceptions.ExceptionCode;
import com.app.exceptions.MyException;
import com.app.model.User;
import com.app.repository.UserRepository;;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User addUser(User user) {

        if (user == null) {
            throw new MyException(ExceptionCode.SERVICE, "User is null");
        }
        return userRepository.save(user);

    }

    public User getOneUser(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new MyException(ExceptionCode.SERVICE, "Id is null"));
    }

    public List<User> getAllUsers() {
        return userRepository
                .findAll();

    }

    public void deleteUser(Long userId) {
        if (userId == null) {
            throw new MyException(ExceptionCode.SERVICE, "Is is null");
        }
        User user = userRepository
                .findById(userId)
                .orElseThrow(NullPointerException::new);
        userRepository.delete(user);
    }
}

