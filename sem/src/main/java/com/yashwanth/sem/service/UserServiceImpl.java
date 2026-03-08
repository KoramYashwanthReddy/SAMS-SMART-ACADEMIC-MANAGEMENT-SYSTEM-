package com.yashwanth.sem.service;

import com.yashwanth.sem.dto.CreateCollegeAdminRequest;
import com.yashwanth.sem.entity.College;
import com.yashwanth.sem.entity.User;
import com.yashwanth.sem.enums.Role;
import com.yashwanth.sem.exception.ResourceNotFoundException;
import com.yashwanth.sem.repository.CollegeRepository;
import com.yashwanth.sem.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createCollegeAdmin(CreateCollegeAdminRequest request) {

        // ================= VALIDATION =================

        if (request.getCollegeId() == null) {
            throw new RuntimeException("College ID is required");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        // ================= FETCH COLLEGE =================

        College college = collegeRepository.findById(request.getCollegeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("College not found"));

        // ================= CREATE USER =================

        User admin = new User();

        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setUsername(request.getUsername());
        admin.setEmail(request.getEmail());

        admin.setPassword(passwordEncoder.encode(request.getPassword()));

        admin.setRole(Role.COLLEGE_ADMIN);
        admin.setActive(true);

        // ================= REQUIRED DATABASE FIELDS =================

        admin.setSystemUserId(generateSystemUserId());

        admin.setCollegeUserId(generateCollegeUserId());

        admin.setCollege(college);

        // ================= SAVE =================

        return userRepository.save(admin);
    }

    // ================= ID GENERATORS =================

    private String generateSystemUserId() {

        return "SYS-" + UUID.randomUUID().toString().substring(0, 8);
    }

    private String generateCollegeUserId() {

        return "COL-ADMIN-" + System.currentTimeMillis();
    }
}