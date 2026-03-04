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

        College college = collegeRepository.findById(request.getCollegeId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("College not found"));

        User admin = new User();

        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setUsername(request.getUsername());
        admin.setEmail(request.getEmail());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setRole(Role.COLLEGE_ADMIN);
        admin.setCollege(college);

        return userRepository.save(admin);
    }
}