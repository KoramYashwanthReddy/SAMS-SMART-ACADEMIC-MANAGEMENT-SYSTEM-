package com.yashwanth.sem.repository;

import com.yashwanth.sem.entity.AcademicYear;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AcademicYearRepository extends JpaRepository<AcademicYear, Long> {

    List<AcademicYear> findByCourseId(Long courseId);

}