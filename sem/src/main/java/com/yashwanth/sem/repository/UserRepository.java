package com.yashwanth.sem.repository;

import com.yashwanth.sem.entity.User;
import com.yashwanth.sem.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Login
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    // Duplicate checks
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    // Super admin check
    boolean existsByRole(Role role);

    // Get users by role
    List<User> findByRole(Role role);

    // Users by college
    List<User> findByCollegeId(Long collegeId);

    // Users by role in college
    List<User> findByCollegeIdAndRole(Long collegeId, Role role);

    // Dashboard counts
    long countByRole(Role role);
}