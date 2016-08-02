package com.epam.suleimenov.model;


import java.util.List;

public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<Course> courses;
    private Role role;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Role getUserRole() {
        return role;
    }

    public void setUserRole(Role role) {
        this.role = role;
    }

    public enum Role {
        TEACHER,
        STUDENT,
        ADMIN
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", courses=" + courses +
                ", role=" + role +
                '}';
    }
}

