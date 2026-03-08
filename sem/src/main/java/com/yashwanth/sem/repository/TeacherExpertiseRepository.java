package com.yashwanth.sem.repository;

import com.yashwanth.sem.entity.TeacherExpertise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherExpertiseRepository extends JpaRepository<TeacherExpertise, Long> {

    List<TeacherExpertise> findByTeacherId(Long teacherId);

}