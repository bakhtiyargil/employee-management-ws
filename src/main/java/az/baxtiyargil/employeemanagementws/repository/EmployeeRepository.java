package az.baxtiyargil.employeemanagementws.repository;

import az.baxtiyargil.employeemanagementws.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author bakhtiyargil on 08.05.2021
 */
@Repository(value = "employeeRepo")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findById(Long id);

    Optional<Employee> findByName(String name);

}
