package az.baxtiyargil.employeemanagementws.controller;

import az.baxtiyargil.employeemanagementws.security.service.AuthenticationService;
import az.baxtiyargil.employeemanagementws.security.model.JwtAuthenticationRequest;
import az.baxtiyargil.employeemanagementws.security.model.JwtAuthenticationResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author bakhtiyargil on 08.05.2021
 */
@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public JwtAuthenticationResponse login(@Valid @RequestBody JwtAuthenticationRequest request) {
        return service.createAuthenticationToken(request);
    }

}
