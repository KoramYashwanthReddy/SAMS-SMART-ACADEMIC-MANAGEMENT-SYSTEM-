package com.yashwanth.sem.repository;

import com.yashwanth.sem.entity.College;
import com.yashwanth.sem.enums.CollegeStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollegeRepository extends JpaRepository<College, Long> {

    boolean existsByCollegeCode(String collegeCode);

    List<College> findByStatus(CollegeStatus status);

    long countByStatus(CollegeStatus status);
}