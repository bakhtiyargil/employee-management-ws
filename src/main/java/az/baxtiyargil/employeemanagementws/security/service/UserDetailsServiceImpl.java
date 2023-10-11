package az.baxtiyargil.employeemanagementws.security.service;

import az.baxtiyargil.employeemanagementws.security.domain.User;
import az.baxtiyargil.employeemanagementws.security.model.UserDetailsImpl;
import az.baxtiyargil.employeemanagementws.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author bakhtiyargil on 08.05.2021
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository repository;

    public UserDetailsServiceImpl(@Qualifier("userRepo") UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND"));
        return new UserDetailsImpl(user);
    }
}
