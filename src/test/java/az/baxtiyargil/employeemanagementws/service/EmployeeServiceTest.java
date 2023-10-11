package az.baxtiyargil.employeemanagementws.service;

import az.baxtiyargil.employeemanagementws.dto.EmployeeDto;
import az.baxtiyargil.employeemanagementws.error.ErrorConstants;
import az.baxtiyargil.employeemanagementws.error.exception.CommonException;
import az.baxtiyargil.employeemanagementws.mapper.EmployeeMapper;
import az.baxtiyargil.employeemanagementws.model.AddEmployeeRequest;
import az.baxtiyargil.employeemanagementws.repository.EmployeeRepository;
import az.baxtiyargil.employeemanagementws.domain.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author bakhtiyargil on 10.05.2021
 */
@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    @Spy
    EmployeeService employeeService;
    @Mock
    private EmployeeRepository employeeRepositoryMock;
    @Mock
    private EmployeeMapper employeeMapperMock;

    @Test
    void findAllEmployees_returnsAllEmployees() {
        //given
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("test");
        employee.setSurname("test");
        employee.setAge(21);

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("test");
        employeeDto.setSurname("test");
        employeeDto.setAge(21);

        //when
        when(employeeMapperMock.toDto(employee)).thenReturn(employeeDto);
        when(employeeRepositoryMock.findAll()).thenReturn(List.of(employee));

        //then

        assertAll(
                () -> assertEquals(employeeService.findAllEmployees(), List.of(employeeDto)),
                () -> verify(employeeRepositoryMock, times(1)).findAll(),
                () -> verify(employeeMapperMock, times(1)).toDto(employee)
        );

    }

    @Test
    void findById_returnsEmployee() {

        //given
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("test");
        employee.setSurname("test");
        employee.setAge(21);

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("test");
        employeeDto.setSurname("test");
        employeeDto.setAge(21);

        //when
        when(employeeMapperMock.toDto(employee)).thenReturn(employeeDto);
        when(employeeRepositoryMock.findById(1L)).thenReturn(Optional.of(employee));

        //then
        assertAll(
                () -> Assertions.assertEquals(employeeService.findById(1L), Optional.of(employeeDto)),
                () -> verify(employeeRepositoryMock, times(1)).findById(1L),
                () -> verify(employeeMapperMock, times(1)).toDto(employee)
        );
    }

    @Test
    void updateEmployee_success() {

        //given
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("test");
        employee.setSurname("test");
        employee.setAge(21);

        Employee employeeUpdated = new Employee();
        employeeUpdated.setId(1L);
        employeeUpdated.setName("test");
        employeeUpdated.setSurname("test");
        employeeUpdated.setAge(21);

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(1L);
        employeeDto.setName("test");
        employeeDto.setSurname("test");
        employeeDto.setAge(21);

        EmployeeDto employeeDtoUpdated = new EmployeeDto();
        employeeDtoUpdated.setId(1L);
        employeeDtoUpdated.setName("salam");
        employeeDtoUpdated.setSurname("test");
        employeeDtoUpdated.setAge(21);

        //when
        when(employeeRepositoryMock.findById(1L)).thenReturn(Optional.of(employee));
        when(employeeMapperMock.toEntity(employeeDtoUpdated)).thenReturn(employeeUpdated);
        when(employeeMapperMock.toDto(employeeUpdated)).thenReturn(employeeDtoUpdated);
        when(employeeRepositoryMock.save(employeeUpdated)).thenReturn(employeeUpdated);

        //then
        assertAll(
                () -> Assertions.assertEquals(employeeService.updateEmployee(employeeDtoUpdated), employeeDtoUpdated),
                () -> verify(employeeRepositoryMock, times(1)).findById(1L),
                () -> verify(employeeRepositoryMock, times(1)).save(employeeUpdated),
                () -> verify(employeeMapperMock, times(1)).toDto(employeeUpdated),
                () -> verify(employeeMapperMock, times(1)).toEntity(employeeDtoUpdated)
        );
    }

    @Test
    void updateEmployee_throws_EmployeeNotFound_Exception() {

        //given
        Employee employeeUpdated = new Employee();
        employeeUpdated.setId(1L);
        employeeUpdated.setName("test");
        employeeUpdated.setSurname("test");
        employeeUpdated.setAge(21);

        EmployeeDto employeeDtoUpdated = new EmployeeDto();
        employeeDtoUpdated.setId(1L);
        employeeDtoUpdated.setName("salam");
        employeeDtoUpdated.setSurname("test");
        employeeDtoUpdated.setAge(21);

        //when
        doReturn(Optional.empty()).when(employeeRepositoryMock).findById(1L);

        //then
        assertAll(
                () -> assertThrows(CommonException.class, () -> employeeService.updateEmployee(employeeDtoUpdated)),
                () -> verify(employeeRepositoryMock, times(1)).findById(1L),
                () -> verify(employeeRepositoryMock, times(0)).save(employeeUpdated),
                () -> verify(employeeMapperMock, times(0)).toDto(employeeUpdated),
                () -> verify(employeeMapperMock, times(0)).toEntity(employeeDtoUpdated)
        );
    }

    @Test
    void addEmployee_success() {
        //given
        AddEmployeeRequest addEmployeeRequest = new AddEmployeeRequest();
        addEmployeeRequest.setName("test");
        addEmployeeRequest.setSurname("test");
        addEmployeeRequest.setAge(21);

        Employee employee = new Employee();
        EmployeeDto employeeDto = new EmployeeDto();

        //when
        when(employeeRepositoryMock.save(employee)).thenReturn(employee);
        when(employeeMapperMock.toDto(employee)).thenReturn(employeeDto);

        //then
        assertAll(
                () -> Assertions.assertEquals(employeeService.addEmployee(addEmployeeRequest), employeeDto), //causes problem
                () -> verify(employeeRepositoryMock, times(1)).save(employee),
                () -> verify(employeeMapperMock, times(1)).toDto(employee)
        );
    }

    @Test
    void deleteEmployeeById_success() {
        //given
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("test");
        employee.setSurname("test");
        employee.setAge(21);

        //when
        doNothing().when(employeeRepositoryMock).deleteById(1L);
        when(employeeRepositoryMock.findById(1L)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployeeById(1L);
        //then
        assertAll(
                () -> verify(employeeRepositoryMock, times(1)).findById(1L),
                () -> verify(employeeRepositoryMock, times(1)).deleteById(1L)
        );
    }

    @Test
    void deleteEmployeeById_fail_noEmployeeExists() {
        //given
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setName("test");
        employee.setSurname("test");
        employee.setAge(21);

        //when
        when(employeeRepositoryMock.findById(1L)).thenThrow(new CommonException(ErrorConstants.EMPLOYEE_NOT_FOUND));

        //then
        CommonException ex = assertThrows(CommonException.class, () -> employeeService.deleteEmployeeById(1L));
        assertAll(
                () -> assertEquals(ErrorConstants.EMPLOYEE_NOT_FOUND, ex.getMessage()),
                () -> verify(employeeRepositoryMock, times(1)).findById(1L),
                () -> verify(employeeRepositoryMock, times(0)).deleteById(1L)
        );
    }
}