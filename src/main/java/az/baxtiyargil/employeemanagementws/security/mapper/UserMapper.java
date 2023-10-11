package az.baxtiyargil.employeemanagementws.security.mapper;

import az.baxtiyargil.employeemanagementws.security.domain.User;
import az.baxtiyargil.employeemanagementws.security.dto.UserDto;
import org.springframework.stereotype.Component;

/**
 * @author bakhtiyargil on 08.05.2021
 */
@Component
public class UserMapper {

    public static User toUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        User user = new User();

        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public static UserDto toDto(User user) {
        if (user == null) {
            return null;
        }
        UserDto userDto = new UserDto();

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setRole(user.getRole().getName());
        return userDto;
    }
}
