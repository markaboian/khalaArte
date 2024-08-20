package com.khala_arte.ms_users.service.interfaces;

import com.khala_arte.ms_users.dto.UserDTO;

import java.util.Optional;
import java.util.Set;

public interface IUserService {

    Optional<UserDTO> getUserById(Long id);
    Optional<UserDTO> getUserByEmail(String email);
    Set<UserDTO> getAllUsers();

    UserDTO addUser(UserDTO userDTO);
    UserDTO updateUser(UserDTO userDTO);

    void deleteUserById(Long id);

}
