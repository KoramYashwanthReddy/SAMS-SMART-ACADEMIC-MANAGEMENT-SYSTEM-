package com.yashwanth.sem.repository;

import com.yashwanth.sem.entity.TimeTable;
import com.yashwanth.sem.entity.Course;
import com.yashwanth.sem.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Long> {

    List<TimeTable> findByCourse(Course course);

    List<TimeTable> findByTeacher(User teacher);

    List<TimeTable> findByDay(String day);

    List<TimeTable> findByCourseAndDay(Course course, String day);

}