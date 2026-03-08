package com.yashwanth.sem.service;

import com.yashwanth.sem.entity.*;

import java.util.List;

public interface HodService {

    // ================= DASHBOARD =================

    long getTotalStudents();

    long getTotalExams();


    // ================= STUDENTS =================

    List<Student> getAllStudents();

    Student createStudent(Student student);

    Student updateStudent(Long id, Student student);

    void blockStudent(Long id);

    void unblockStudent(Long id);


    // ================= EXAMS =================

    Exam createExam(Exam exam);

    List<Exam> getAllExams();


    // ================= APPROVALS =================

    ApprovalRequest approveRequest(Long approvalId);

    List<ApprovalRequest> getPendingApprovals();


    // ================= TIMETABLE =================

    TimeTable createTimeTable(TimeTable timeTable);

    List<TimeTable> getTimeTable();


    // ================= INTERNAL MARKS =================

    InternalMark addInternalMarks(InternalMark internalMark);


    // ================= RESULTS =================

    Result uploadResult(Result result);


    // ================= NOTIFICATIONS =================

    List<Notification> getNotifications();

}