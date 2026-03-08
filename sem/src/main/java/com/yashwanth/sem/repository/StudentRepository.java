package com.yashwanth.sem.repository;

import com.yashwanth.sem.entity.Student;
import com.yashwanth.sem.entity.Course;
import com.yashwanth.sem.entity.AcademicYear;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByRollNumber(String rollNumber);

    List<Student> findByYear(int year);

    List<Student> findByCourse(Course course);

    List<Student> findByAcademicYear(AcademicYear academicYear);

    List<Student> findByCourseAndYear(Course course, int year);

    List<Student> findByCourseAndAcademicYear(Course course, AcademicYear academicYear);

    List<Student> findByActive(boolean active);

    List<Student> findByApproved(boolean approved);

    boolean existsByRollNumber(String rollNumber);

}