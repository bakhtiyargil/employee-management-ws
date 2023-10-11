package az.baxtiyargil.employeemanagementws.model;

import javax.validation.constraints.NotNull;

/**
 * @author bakhtiyargil on 09.05.2021
 */
public class AddEmployeeRequest {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private int age;

    public AddEmployeeRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
