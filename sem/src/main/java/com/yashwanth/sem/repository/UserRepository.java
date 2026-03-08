package com.yashwanth.sem.repository;

import com.yashwanth.sem.entity.User;
import com.yashwanth.sem.enums.Role;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // ================= LOGIN =================

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findBySystemUserId(String systemUserId);

    Optional<User> findByCollegeUserId(String collegeUserId);

    // ================= DUPLICATE CHECKS =================

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsBySystemUserId(String systemUserId);

    boolean existsByCollegeUserId(String collegeUserId);

    // ================= ROLE CHECK =================

    boolean existsByRole(Role role);

    // ================= GET USERS BY ROLE =================

    List<User> findByRole(Role role);

    // ================= USERS BY COLLEGE =================

    List<User> findByCollege_Id(Long collegeId);

    // ================= USERS BY ROLE IN COLLEGE =================

    List<User> findByCollege_IdAndRole(Long collegeId, Role role);

    // ================= USERS BY DEPARTMENT =================

    List<User> findByDepartment(String department);

    // ================= USERS BY DEPARTMENT AND ROLE =================

    List<User> findByDepartmentAndRole(String department, Role role);

    // ================= USERS BY COLLEGE + DEPARTMENT =================

    List<User> findByCollege_IdAndDepartment(Long collegeId, String department);

    // ================= TEACHERS IN DEPARTMENT =================

    List<User> findByCollege_IdAndDepartmentAndRole(
            Long collegeId,
            String department,
            Role role
    );

    // ================= DASHBOARD COUNTS =================

    long countByRole(Role role);

    long countByCollege_Id(Long collegeId);

    long countByDepartment(String department);

}