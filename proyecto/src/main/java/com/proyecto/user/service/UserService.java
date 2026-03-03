package com.proyecto.user.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.proyecto.user.dto.UserCreateDTO;
import com.proyecto.user.dto.UserDTO;
import com.proyecto.user.dto.UserUpdateDTO;
import com.proyecto.user.entity.User;
import com.proyecto.user.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(/* mapear campos de User a UserDTO */))
                .toList();
    }

    public UserDTO getUserById(UUID id) {
        return userRepository.findById(id)
                .map(user -> new UserDTO(user.getId(), user.getUserName(), user.getEmail()))
                .orElse(null);
    }

    public UserDTO createUser(UserCreateDTO createUserDTO) {
        User user = new User();
        user.setUserName(createUserDTO.getUserName());
        user.setEmail(createUserDTO.getEmail());
        User savedUser = userRepository.save(user);
        return new UserDTO(savedUser.getId(), savedUser.getUserName(), savedUser.getEmail());
    }

    public UserDTO updateUser(Long id, UserUpdateDTO updateUserDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

    public void deleteUser(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    }

}
