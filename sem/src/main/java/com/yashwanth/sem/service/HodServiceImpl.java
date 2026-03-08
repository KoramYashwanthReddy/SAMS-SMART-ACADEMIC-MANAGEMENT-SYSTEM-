package com.yashwanth.sem.service;

import com.yashwanth.sem.entity.*;
import com.yashwanth.sem.repository.*;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HodServiceImpl implements HodService {

    private final StudentRepository studentRepository;
    private final ExamRepository examRepository;
    private final ApprovalRepository approvalRepository;
    private final TimeTableRepository timeTableRepository;
    private final InternalMarkRepository internalMarkRepository;
    private final ResultRepository resultRepository;
    private final NotificationRepository notificationRepository;

    public HodServiceImpl(
            StudentRepository studentRepository,
            ExamRepository examRepository,
            ApprovalRepository approvalRepository,
            TimeTableRepository timeTableRepository,
            InternalMarkRepository internalMarkRepository,
            ResultRepository resultRepository,
            NotificationRepository notificationRepository) {

        this.studentRepository = studentRepository;
        this.examRepository = examRepository;
        this.approvalRepository = approvalRepository;
        this.timeTableRepository = timeTableRepository;
        this.internalMarkRepository = internalMarkRepository;
        this.resultRepository = resultRepository;
        this.notificationRepository = notificationRepository;
    }

    // ================= DASHBOARD =================

    @Override
    public long getTotalStudents() {
        return studentRepository.count();
    }

    @Override
    public long getTotalExams() {
        return examRepository.count();
    }

    // ================= STUDENTS =================

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long id, Student student) {

        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        existing.setPhone(student.getPhone());
        existing.setYear(student.getYear());

        return studentRepository.save(existing);
    }

    @Override
    public void blockStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setActive(false);

        studentRepository.save(student);
    }

    @Override
    public void unblockStudent(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setActive(true);

        studentRepository.save(student);
    }

    // ================= EXAMS =================

    @Override
    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    // ================= APPROVALS =================

    @Override
    public ApprovalRequest approveRequest(Long approvalId) {

        ApprovalRequest request = approvalRepository.findById(approvalId)
                .orElseThrow(() -> new RuntimeException("Approval not found"));

        request.setApproved(true);

        return approvalRepository.save(request);
    }

    @Override
    public List<ApprovalRequest> getPendingApprovals() {
        return approvalRepository.findByApproved(false);
    }

    // ================= TIMETABLE =================

    @Override
    public TimeTable createTimeTable(TimeTable timeTable) {
        return timeTableRepository.save(timeTable);
    }

    @Override
    public List<TimeTable> getTimeTable() {
        return timeTableRepository.findAll();
    }

    // ================= INTERNAL MARKS =================

    @Override
    public InternalMark addInternalMarks(InternalMark internalMark) {
        return internalMarkRepository.save(internalMark);
    }

    // ================= RESULTS =================

    @Override
    public Result uploadResult(Result result) {
        return resultRepository.save(result);
    }

    // ================= NOTIFICATIONS =================

    @Override
    public List<Notification> getNotifications() {
        return notificationRepository.findAll();
    }

}