package az.baxtiyargil.employeemanagementws.service;

import az.baxtiyargil.employeemanagementws.dto.EmployeeDto;
import az.baxtiyargil.employeemanagementws.error.ErrorConstants;
import az.baxtiyargil.employeemanagementws.error.exception.CommonException;
import az.baxtiyargil.employeemanagementws.mapper.EmployeeMapper;
import az.baxtiyargil.employeemanagementws.model.AddEmployeeRequest;
import az.baxtiyargil.employeemanagementws.repository.EmployeeRepository;
import az.baxtiyargil.employeemanagementws.domain.Employee;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bakhtiyargil on 08.05.2021
 */
@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeService(@Qualifier("employeeRepo") EmployeeRepository employeeRepository,
                           EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Transactional(readOnly = true)
    public List<EmployeeDto> findAllEmployees() {
        return employeeRepository.findAll().stream().map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EmployeeDto findById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new CommonException(ErrorConstants.EMPLOYEE_NOT_FOUND));

        return employeeMapper.toDto(employee);
    }

    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {

        Employee employee = employeeRepository.findById(employeeDto.getId())
                .orElseThrow(() -> new CommonException(ErrorConstants.EMPLOYEE_NOT_FOUND));
        employee = employeeMapper.toEntity(employeeDto);
        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    public EmployeeDto addEmployee(AddEmployeeRequest addEmployeeRequest) {

        Employee employee = new Employee();
        employee.setName(addEmployeeRequest.getName());
        employee.setSurname(addEmployeeRequest.getSurname());
        employee.setAge(addEmployeeRequest.getAge());

        employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    public void deleteEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new CommonException(ErrorConstants.EMPLOYEE_NOT_FOUND));
        employeeRepository.deleteById(employee.getId());
    }
}
