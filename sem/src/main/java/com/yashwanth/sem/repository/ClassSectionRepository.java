package com.yashwanth.sem.repository;

import com.yashwanth.sem.entity.ClassSection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassSectionRepository extends JpaRepository<ClassSection, Long> {

    List<ClassSection> findByAcademicYearId(Long academicYearId);

}