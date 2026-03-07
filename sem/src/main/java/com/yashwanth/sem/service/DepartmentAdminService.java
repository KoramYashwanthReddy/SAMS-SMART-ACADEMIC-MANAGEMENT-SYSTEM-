package com.yashwanth.sem.service;

import com.yashwanth.sem.entity.Course;
import com.yashwanth.sem.entity.Subject;
import com.yashwanth.sem.entity.TeacherSubject;
import com.yashwanth.sem.entity.User;
import com.yashwanth.sem.enums.Role;
import com.yashwanth.sem.repository.CourseRepository;
import com.yashwanth.sem.repository.SubjectRepository;
import com.yashwanth.sem.repository.TeacherSubjectRepository;
import com.yashwanth.sem.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentAdminService {

    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherSubjectRepository teacherSubjectRepository;
    private final UserRepository userRepository;

    public DepartmentAdminService(CourseRepository courseRepository,
                                  SubjectRepository subjectRepository,
                                  TeacherSubjectRepository teacherSubjectRepository,
                                  UserRepository userRepository) {

        this.courseRepository = courseRepository;
        this.subjectRepository = subjectRepository;
        this.teacherSubjectRepository = teacherSubjectRepository;
        this.userRepository = userRepository;
    }

    // ================= ASSIGN / UPDATE HOD =================

    public Course assignOrUpdateHod(Long courseId, Long teacherId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        if (teacher.getRole() != Role.TEACHER && teacher.getRole() != Role.HOD) {
            throw new RuntimeException("Only teachers can become HOD");
        }

        course.setHodId(teacherId);

        return courseRepository.save(course);
    }

    // ================= CREATE SUBJECT =================

    public Subject createSubject(Subject subject) {

        courseRepository.findById(subject.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return subjectRepository.save(subject);
    }

    // ================= BULK SUBJECT CREATION =================

    public List<Subject> createSubjects(List<Subject> subjects) {

        for (Subject subject : subjects) {
            courseRepository.findById(subject.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found for subject: " + subject.getName()));
        }

        return subjectRepository.saveAll(subjects);
    }

    // ================= UPDATE SUBJECT =================

    public Subject updateSubject(Long subjectId, Subject updatedSubject){

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        subject.setName(updatedSubject.getName());
        subject.setCode(updatedSubject.getCode());
        subject.setHoursPerWeek(updatedSubject.getHoursPerWeek());

        return subjectRepository.save(subject);
    }

    // ================= DELETE SUBJECT =================

    public void deleteSubject(Long subjectId){

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        subjectRepository.delete(subject);
    }

    // ================= GET SUBJECTS BY COURSE =================

    public List<Subject> getSubjectsByCourse(Long courseId){

        courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return subjectRepository.findByCourseId(courseId);
    }

    // ================= ASSIGN TEACHER =================

    public String assignTeacherToSubject(Long teacherId,
                                         Long subjectId,
                                         int hours){

        User teacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        if (teacher.getRole() != Role.TEACHER && teacher.getRole() != Role.HOD) {
            throw new RuntimeException("Only teachers or HOD can teach subjects");
        }

        if (!teacherSubjectRepository.findBySubjectId(subjectId).isEmpty()) {
            throw new RuntimeException("Subject already assigned to a teacher");
        }

        int totalHours = teacherSubjectRepository.sumHoursByTeacher(teacherId);

        if (totalHours + hours > 5) {
            throw new RuntimeException("Teacher workload exceeded 5 hours across college");
        }

        TeacherSubject assignment = new TeacherSubject();

        assignment.setTeacherId(teacherId);
        assignment.setSubjectId(subjectId);
        assignment.setHours(hours);

        teacherSubjectRepository.save(assignment);

        return "Teacher assigned successfully";
    }

    // ================= REPLACE TEACHER =================

    public String replaceTeacher(Long subjectId,
                                 Long newTeacherId,
                                 int hours){

        List<TeacherSubject> existing = teacherSubjectRepository.findBySubjectId(subjectId);

        if(existing.isEmpty()){
            throw new RuntimeException("Subject has no assigned teacher yet");
        }

        TeacherSubject assignment = existing.get(0);

        int totalHours = teacherSubjectRepository.sumHoursByTeacher(newTeacherId);

        if(totalHours + hours > 5){
            throw new RuntimeException("Teacher workload exceeded");
        }

        assignment.setTeacherId(newTeacherId);
        assignment.setHours(hours);

        teacherSubjectRepository.save(assignment);

        return "Teacher replaced successfully";
    }

    // ================= TEACHER WORKLOAD =================

    public int getTeacherWorkload(Long teacherId){

        return teacherSubjectRepository.sumHoursByTeacher(teacherId);
    }

    // ================= SUBJECTS TAUGHT BY TEACHER =================

    public List<TeacherSubject> getTeacherSubjects(Long teacherId){

        return teacherSubjectRepository.findByTeacherId(teacherId);
    }
}