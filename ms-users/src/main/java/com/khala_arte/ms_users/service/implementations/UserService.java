package com.khala_arte.ms_users.service.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.khala_arte.ms_users.domain.User;
import com.khala_arte.ms_users.dto.UserDTO;
import com.khala_arte.ms_users.repository.IUserRepository;
import com.khala_arte.ms_users.service.interfaces.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final ObjectMapper mapper;

    private UserDTO saveUser(UserDTO userDTO) {
        User user = mapper.convertValue(userDTO, User.class);
        userRepository.save(user);
        return mapper.convertValue(user, UserDTO.class);
    }

    @Override
    public Optional<UserDTO> getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        UserDTO userDTO;
        if (user.isPresent()) {
            userDTO = mapper.convertValue(user, UserDTO.class);
            return Optional.ofNullable(userDTO);
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserDTO> getUserByEmail(String email) {
        Optional<User> user = userRepository.findUserByEmail(email);
        UserDTO userDTO;
        if (user.isPresent()) {
            userDTO = mapper.convertValue(user, UserDTO.class);
            return Optional.ofNullable(userDTO);
        }
        else {
            return Optional.empty();
        }
    }

    @Override
    public Set<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> mapper.convertValue(user, UserDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {
        return saveUser(userDTO);
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        return saveUser(userDTO);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
