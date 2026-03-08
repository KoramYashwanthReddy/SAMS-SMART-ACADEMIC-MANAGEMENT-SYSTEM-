package com.yashwanth.sem.controller;

import com.yashwanth.sem.entity.*;
import com.yashwanth.sem.service.HodService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hod")
public class HodController {

    private final HodService hodService;

    public HodController(HodService hodService) {
        this.hodService = hodService;
    }

    // ================= DASHBOARD =================

    @GetMapping("/dashboard")
    public ResponseEntity<String> getDashboard() {

        long students = hodService.getTotalStudents();
        long exams = hodService.getTotalExams();

        return ResponseEntity.ok(
                "Total Students: " + students +
                " | Total Exams: " + exams
        );
    }

    // ================= STUDENTS =================

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(hodService.getAllStudents());
    }

    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(hodService.createStudent(student));
    }

    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long id,
            @RequestBody Student student) {

        return ResponseEntity.ok(hodService.updateStudent(id, student));
    }

    @PostMapping("/students/block/{id}")
    public ResponseEntity<String> blockStudent(@PathVariable Long id) {

        hodService.blockStudent(id);
        return ResponseEntity.ok("Student blocked successfully");
    }

    @PostMapping("/students/unblock/{id}")
    public ResponseEntity<String> unblockStudent(@PathVariable Long id) {

        hodService.unblockStudent(id);
        return ResponseEntity.ok("Student unblocked successfully");
    }

    // ================= EXAMS =================

    @PostMapping("/exams")
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) {
        return ResponseEntity.ok(hodService.createExam(exam));
    }

    @GetMapping("/exams")
    public ResponseEntity<List<Exam>> getExams() {
        return ResponseEntity.ok(hodService.getAllExams());
    }

    // ================= APPROVALS =================

    @PutMapping("/approvals/{id}")
    public ResponseEntity<ApprovalRequest> approveRequest(@PathVariable Long id) {

        return ResponseEntity.ok(hodService.approveRequest(id));
    }

    @GetMapping("/approvals/pending")
    public ResponseEntity<List<ApprovalRequest>> getPendingApprovals() {

        return ResponseEntity.ok(hodService.getPendingApprovals());
    }

    // ================= TIMETABLE =================

    @PostMapping("/timetable")
    public ResponseEntity<TimeTable> createTimeTable(@RequestBody TimeTable timeTable) {

        return ResponseEntity.ok(hodService.createTimeTable(timeTable));
    }

    @GetMapping("/timetable")
    public ResponseEntity<List<TimeTable>> getTimeTable() {

        return ResponseEntity.ok(hodService.getTimeTable());
    }

    // ================= INTERNAL MARKS =================

    @PostMapping("/internal")
    public ResponseEntity<InternalMark> addInternalMarks(@RequestBody InternalMark internalMark) {

        return ResponseEntity.ok(hodService.addInternalMarks(internalMark));
    }

    // ================= RESULTS =================

    @PostMapping("/results")
    public ResponseEntity<Result> uploadResult(@RequestBody Result result) {

        return ResponseEntity.ok(hodService.uploadResult(result));
    }

    // ================= NOTIFICATIONS =================

    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> getNotifications() {

        return ResponseEntity.ok(hodService.getNotifications());
    }

}