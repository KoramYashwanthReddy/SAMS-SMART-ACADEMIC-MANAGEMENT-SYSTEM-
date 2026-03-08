package com.yashwanth.sem.repository;

import com.yashwanth.sem.entity.InternalMark;
import com.yashwanth.sem.entity.Student;
import com.yashwanth.sem.entity.Subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InternalMarkRepository extends JpaRepository<InternalMark, Long> {

    List<InternalMark> findByStudent(Student student);

    List<InternalMark> findBySubject(Subject subject);

    Optional<InternalMark> findByStudentAndSubject(Student student, Subject subject);

}