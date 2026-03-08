package com.yashwanth.sem.service;

import com.yashwanth.sem.entity.AcademicYear;
import com.yashwanth.sem.entity.Course;
import com.yashwanth.sem.entity.Subject;
import com.yashwanth.sem.entity.User;
import com.yashwanth.sem.enums.Role;
import com.yashwanth.sem.repository.AcademicYearRepository;
import com.yashwanth.sem.repository.CourseRepository;
import com.yashwanth.sem.repository.SubjectRepository;
import com.yashwanth.sem.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentAdminService {

    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;
    private final AcademicYearRepository academicYearRepository;

    public DepartmentAdminService(CourseRepository courseRepository,
                                  SubjectRepository subjectRepository,
                                  UserRepository userRepository,
                                  AcademicYearRepository academicYearRepository) {

        this.courseRepository = courseRepository;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
        this.academicYearRepository = academicYearRepository;
    }

    // ================= ASSIGN / UPDATE HOD =================

    public Course assignOrUpdateHod(Long courseId, Long teacherId) {

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        User newTeacher = userRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        if (newTeacher.getRole() != Role.TEACHER && newTeacher.getRole() != Role.HOD) {
            throw new RuntimeException("Only teachers can be assigned as HOD");
        }

        // STEP 1: revert old HOD role back to TEACHER
        if (course.getHodId() != null) {

            User oldHod = userRepository.findById(course.getHodId())
                    .orElseThrow(() -> new RuntimeException("Old HOD not found"));

            oldHod.setRole(Role.TEACHER);
            userRepository.save(oldHod);
        }

        // STEP 2: assign new HOD
        newTeacher.setRole(Role.HOD);
        userRepository.save(newTeacher);

        // STEP 3: update course
        course.setHodId(teacherId);

        return courseRepository.save(course);
    }

    // ================= UPDATE CLASS NAME =================

    public AcademicYear updateClassName(Long academicYearId, String className){

        AcademicYear year = academicYearRepository.findById(academicYearId)
                .orElseThrow(() -> new RuntimeException("Academic year not found"));

        year.setClassName(className);

        return academicYearRepository.save(year);
    }

    // ================= CREATE SUBJECT =================

    public Subject createSubject(Subject subject) {

        academicYearRepository.findById(subject.getAcademicYearId())
                .orElseThrow(() -> new RuntimeException("Academic year not found"));

        return subjectRepository.save(subject);
    }

    // ================= BULK SUBJECT CREATION =================

    public List<Subject> createSubjects(List<Subject> subjects) {

        for (Subject subject : subjects) {

            academicYearRepository.findById(subject.getAcademicYearId())
                    .orElseThrow(() ->
                            new RuntimeException("Academic year not found for subject: " + subject.getName()));
        }

        return subjectRepository.saveAll(subjects);
    }

    // ================= UPDATE SUBJECT =================

    public Subject updateSubject(Long subjectId, Subject updatedSubject) {

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        subject.setName(updatedSubject.getName());
        subject.setCode(updatedSubject.getCode());
        subject.setHoursPerWeek(updatedSubject.getHoursPerWeek());

        return subjectRepository.save(subject);
    }

    // ================= DELETE SUBJECT =================

    public void deleteSubject(Long subjectId) {

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        subjectRepository.delete(subject);
    }

    // ================= GET SUBJECTS BY ACADEMIC YEAR =================

    public List<Subject> getSubjectsByAcademicYear(Long academicYearId) {

        academicYearRepository.findById(academicYearId)
                .orElseThrow(() -> new RuntimeException("Academic year not found"));

        return subjectRepository.findByAcademicYearId(academicYearId);
    }
}