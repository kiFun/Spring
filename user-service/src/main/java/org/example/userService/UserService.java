package org.example.userService;

import org.example.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO request);
    UserDTO getUserById(long id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(UserDTO request);
    UserDTO deleteUser(long id);
}
