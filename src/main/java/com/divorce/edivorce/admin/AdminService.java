package com.divorce.edivorce.admin;

import com.divorce.edivorce.model.Role;
import com.divorce.edivorce.model.User;
import com.divorce.edivorce.model.UserDTO;
import com.divorce.edivorce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserDTO> getUsers() {
        List<User> users = this.userRepository.findAll();
        return users
                .stream().map(user -> {
                    UserDTO dto = UserDTO.builder()
                            .id(user.getId())
                            .firstname(user.getFirstname())
                            .lastname(user.getLastname())
                            .username(user.getUsername())
                            .socialSecurityNumber(user.getSocialSecurityNumber())
                            .taxIdentificationNumber(user.getTaxIdentificationNumber())
                            .build();
                    return dto;
                        }).collect(Collectors.toList());

    }

    public UserDTO getUser(UUID id) {
        User user = userRepository.findUserById(id);
        return UserDTO.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .socialSecurityNumber(user.getSocialSecurityNumber())
                .taxIdentificationNumber(user.getTaxIdentificationNumber())
                .build();
    }

    public void deleteUser(UUID id) {
        userRepository.deleteUserById(id);
    }

    public UserDTO updateUser(UUID id, UpdateUserRequest request) {
        System.err.println(request);
        User user = userRepository.findUserById(id);
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setUsername(request.getUsername());
        user.setSocialSecurityNumber(request.getSocialSecurityNumber());
        user.setTaxIdentificationNumber(request.getTaxIdentificationNumber());
        userRepository.save(user);
        return UserDTO.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .socialSecurityNumber(user.getSocialSecurityNumber())
                .taxIdentificationNumber(user.getTaxIdentificationNumber())
                .build();
    }

    public UserDTO addUser(CreateUserRequest request) {
        User user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .username(request.getUsername())
                .socialSecurityNumber(request.getSocialSecurityNumber())
                .taxIdentificationNumber(request.getTaxIdentificationNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole()))
                .build();
        userRepository.save(user);
        return UserDTO.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .username(user.getUsername())
                .socialSecurityNumber(user.getSocialSecurityNumber())
                .taxIdentificationNumber(user.getTaxIdentificationNumber())
                .build();
    }
}
