package com.yashwanth.sem.dto;

import com.yashwanth.sem.enums.Role;

public class CreateUserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    private String department;

    // ID given by college
    private String collegeUserId;

    private Role role;

    private Long collegeId;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCollegeUserId() {
        return collegeUserId;
    }

    public void setCollegeUserId(String collegeUserId) {
        this.collegeUserId = collegeUserId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }
}