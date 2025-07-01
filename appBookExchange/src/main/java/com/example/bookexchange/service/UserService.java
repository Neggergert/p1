package com.example.bookexchange.service;

import com.example.bookexchange.dto.CreateUserRequest;
import com.example.bookexchange.dto.UserDto;
import com.example.bookexchange.model.User;
import com.example.bookexchange.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto createUser(CreateUserRequest request) {
        User user = new User(request.name());
        userRepository.save(user);
        return DtoMapper.toDto(user);
    }

    @Transactional(readOnly = true)
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream().map(DtoMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
