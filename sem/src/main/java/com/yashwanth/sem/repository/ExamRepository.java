package com.yashwanth.sem.repository;

import com.yashwanth.sem.entity.Exam;
import com.yashwanth.sem.entity.Subject;
import com.yashwanth.sem.entity.Course;
import com.yashwanth.sem.entity.AcademicYear;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    List<Exam> findBySubject(Subject subject);

    List<Exam> findByCourse(Course course);

    List<Exam> findByAcademicYear(AcademicYear academicYear);

    List<Exam> findByExamDate(LocalDate examDate);

    List<Exam> findByApproved(boolean approved);

    List<Exam> findByPublished(boolean published);

    List<Exam> findByCourseAndAcademicYear(Course course, AcademicYear academicYear);

}