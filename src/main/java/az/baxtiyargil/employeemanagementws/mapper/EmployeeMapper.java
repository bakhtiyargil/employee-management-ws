package az.baxtiyargil.employeemanagementws.mapper;

import az.baxtiyargil.employeemanagementws.domain.Employee;
import az.baxtiyargil.employeemanagementws.dto.EmployeeDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bakhtiyargil on 08.05.2021
 */
@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeDto dto) {
        if (dto == null) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setSurname(dto.getSurname());
        employee.setAge(dto.getAge());
        return employee;
    }

    public List<Employee> toEntity(List<EmployeeDto> dtoList) {
        if (dtoList == null) {
            return null;
        }

        List<Employee> list = new ArrayList<>(dtoList.size());
        for (EmployeeDto employeeDto : dtoList) {
            list.add(toEntity(employeeDto));
        }
        return list;
    }

    public EmployeeDto toDto(Employee entity) {
        if (entity == null) {
            return null;
        }

        EmployeeDto incomingPaymentDto = new EmployeeDto();

        incomingPaymentDto.setId(entity.getId());
        incomingPaymentDto.setName(entity.getName());
        incomingPaymentDto.setSurname(entity.getSurname());
        incomingPaymentDto.setAge(entity.getAge());

        return incomingPaymentDto;
    }

    public List<EmployeeDto> toDto(List<Employee> entityList) {
        if (entityList == null) {
            return null;
        }

        List<EmployeeDto> list = new ArrayList<>(entityList.size());
        for (Employee employee : entityList) {
            list.add(toDto(employee));
        }
        return list;
    }
}
