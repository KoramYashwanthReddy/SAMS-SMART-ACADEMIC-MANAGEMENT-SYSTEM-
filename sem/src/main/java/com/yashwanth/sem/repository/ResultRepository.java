package com.yashwanth.sem.repository;

import com.yashwanth.sem.entity.Result;
import com.yashwanth.sem.entity.Student;
import com.yashwanth.sem.entity.Exam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    List<Result> findByStudent(Student student);

    List<Result> findByExam(Exam exam);

    List<Result> findByStudentAndExam(Student student, Exam exam);

    List<Result> findByPublished(boolean published);

}