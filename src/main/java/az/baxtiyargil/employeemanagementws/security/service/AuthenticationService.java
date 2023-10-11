package az.baxtiyargil.employeemanagementws.security.service;

import az.baxtiyargil.employeemanagementws.security.domain.User;
import az.baxtiyargil.employeemanagementws.error.exception.CommonException;
import az.baxtiyargil.employeemanagementws.security.model.JwtAuthenticationRequest;
import az.baxtiyargil.employeemanagementws.security.model.JwtAuthenticationResponse;
import az.baxtiyargil.employeemanagementws.security.repository.UserRepository;
import az.baxtiyargil.employeemanagementws.security.util.TokenUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author bakhtiyargil on 08.05.2021
 */
@Service
public class AuthenticationService {

    private final UserRepository repository;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(@Qualifier("userRepo") UserRepository repository,
                                 AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
    }

    public JwtAuthenticationResponse createAuthenticationToken(JwtAuthenticationRequest request) {

        authenticate(request.getUsername(), request.getPassword());

        User user = repository.findByUsername(request.getUsername()).get();

        String token = TokenUtil.generateToken(request.getUsername(),
                Arrays.asList(user.getRole().getName()));

        return new JwtAuthenticationResponse(token);
    }

    public void authenticate(String username, String password) {
        Authentication authResult = null;
        try {
            authResult = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (BadCredentialsException e) {
            throw new CommonException("USERNAME_OR_PASSWORD_INCORRECT");
        } catch (DisabledException | LockedException e) {
            throw new CommonException("USER_LOCKED");
        } catch (UsernameNotFoundException e) {
            throw new CommonException("USER_NOT_FOUND");
        } catch (AuthenticationException e) {
            throw new CommonException("USER_NOT_AUTHENTICATED");
        }
    }
}
