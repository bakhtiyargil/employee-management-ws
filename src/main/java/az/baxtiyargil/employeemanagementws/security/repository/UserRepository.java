package az.baxtiyargil.employeemanagementws.security.repository;

import az.baxtiyargil.employeemanagementws.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author bakhtiyargil on 08.05.2021
 */
@Repository(value = "userRepo")
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);
}
