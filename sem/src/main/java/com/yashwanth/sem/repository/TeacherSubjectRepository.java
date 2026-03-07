package com.yashwanth.sem.repository;

import com.yashwanth.sem.entity.TeacherSubject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherSubjectRepository extends JpaRepository<TeacherSubject, Long> {

    // ================= TEACHER WORKLOAD =================

    @Query("SELECT COALESCE(SUM(t.hours),0) FROM TeacherSubject t WHERE t.teacherId = :teacherId")
    int sumHoursByTeacher(@Param("teacherId") Long teacherId);

    // ================= DUPLICATE ASSIGNMENT CHECK =================

    boolean existsByTeacherIdAndSubjectId(Long teacherId, Long subjectId);

    // ================= SUBJECTS TAUGHT BY TEACHER =================

    List<TeacherSubject> findByTeacherId(Long teacherId);

    // ================= TEACHER OF SUBJECT =================

    List<TeacherSubject> findBySubjectId(Long subjectId);

}