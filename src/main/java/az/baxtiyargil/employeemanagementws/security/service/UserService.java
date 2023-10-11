package az.baxtiyargil.employeemanagementws.security.service;

import az.baxtiyargil.employeemanagementws.security.domain.User;
import az.baxtiyargil.employeemanagementws.security.domain.UserRole;
import az.baxtiyargil.employeemanagementws.security.mapper.UserMapper;
import az.baxtiyargil.employeemanagementws.security.dto.UserDto;
import az.baxtiyargil.employeemanagementws.security.model.CreateUserRequest;
import az.baxtiyargil.employeemanagementws.security.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author bakhtiyargil on 08.05.2021
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto addUser(CreateUserRequest request) {

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(UserRole.of(request.getRole()));


        newUser = userRepository.save(newUser);
        return UserMapper.toDto(newUser);

    }
}
