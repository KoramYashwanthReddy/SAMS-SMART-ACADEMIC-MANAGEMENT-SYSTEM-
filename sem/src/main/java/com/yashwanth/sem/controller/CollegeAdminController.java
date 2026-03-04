package com.yashwanth.sem.controller;

import com.yashwanth.sem.dto.CreateUserRequest;
import com.yashwanth.sem.entity.User;
import com.yashwanth.sem.enums.Role;
import com.yashwanth.sem.service.CollegeAdminService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/college-admin")
public class CollegeAdminController {

    private final CollegeAdminService service;

    public CollegeAdminController(CollegeAdminService service) {
        this.service = service;
    }

    // ================= CREATE USERS =================

    @PostMapping("/users")
    public User createUser(@RequestBody CreateUserRequest request) {

        return service.createUser(request);
    }

    // ================= GET ALL USERS IN COLLEGE =================

    @GetMapping("/users/college/{collegeId}")
    public List<User> getUsersByCollege(@PathVariable Long collegeId) {

        return service.getUsersByCollege(collegeId);
    }

    // ================= GET USERS BY ROLE =================

    @GetMapping("/users/{collegeId}/{role}")
    public List<User> getUsersByRole(
            @PathVariable Long collegeId,
            @PathVariable Role role) {

        return service.getUsersByRole(collegeId, role);
    }

    // ================= UPDATE USER =================

    @PutMapping("/users/{id}")
    public User updateUser(
            @PathVariable Long id,
            @RequestBody CreateUserRequest request) {

        return service.updateUser(id, request);
    }

    // ================= DEACTIVATE USER =================

    @DeleteMapping("/users/{id}")
    public String deactivateUser(@PathVariable Long id) {

        service.deactivateUser(id);

        return "User deactivated successfully";
    }
}