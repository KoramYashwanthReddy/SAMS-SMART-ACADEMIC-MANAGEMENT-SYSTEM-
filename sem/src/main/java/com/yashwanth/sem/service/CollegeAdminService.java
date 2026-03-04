package com.yashwanth.sem.service;

import com.yashwanth.sem.dto.CreateUserRequest;
import com.yashwanth.sem.entity.College;
import com.yashwanth.sem.entity.User;
import com.yashwanth.sem.enums.Role;
import com.yashwanth.sem.repository.CollegeRepository;
import com.yashwanth.sem.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeAdminService {

    private final UserRepository userRepository;
    private final CollegeRepository collegeRepository;
    private final PasswordEncoder passwordEncoder;

    public CollegeAdminService(UserRepository userRepository,
                               CollegeRepository collegeRepository,
                               PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.collegeRepository = collegeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ================= CREATE ROLE USERS =================

    public User createUser(CreateUserRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        College college = collegeRepository.findById(request.getCollegeId())
                .orElseThrow(() -> new RuntimeException("College not found"));

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        // 🔐 HASH PASSWORD HERE
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setRole(request.getRole());
        user.setCollege(college);
        user.setActive(true);

        return userRepository.save(user);
    }

    // ================= GET USERS BY COLLEGE =================

    public List<User> getUsersByCollege(Long collegeId) {

        return userRepository.findByCollegeId(collegeId);
    }

    // ================= GET USERS BY ROLE =================

    public List<User> getUsersByRole(Long collegeId, Role role) {

        return userRepository.findByCollegeIdAndRole(collegeId, role);
    }

    // ================= UPDATE USER =================

    public User updateUser(Long id, CreateUserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        return userRepository.save(user);
    }

    // ================= DEACTIVATE USER =================

    public void deactivateUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setActive(false);

        userRepository.save(user);
    }
}