package az.baxtiyargil.employeemanagementws.controller;

import az.baxtiyargil.employeemanagementws.security.service.UserService;
import az.baxtiyargil.employeemanagementws.security.dto.UserDto;
import az.baxtiyargil.employeemanagementws.security.model.CreateUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bakhtiyargil on 08.05.2021
 */
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserDto> createUser(@Validated @RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(userService.addUser(createUserRequest));
    }
}
