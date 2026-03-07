package com.yashwanth.sem.controller;

import com.yashwanth.sem.entity.Course;
import com.yashwanth.sem.entity.Subject;
import com.yashwanth.sem.entity.TeacherSubject;
import com.yashwanth.sem.service.DepartmentAdminService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dept-admin")
public class DepartmentAdminController {

    private final DepartmentAdminService service;

    public DepartmentAdminController(DepartmentAdminService service){
        this.service = service;
    }

    // ================= ASSIGN / UPDATE HOD =================

    @PutMapping("/assign-hod/{courseId}/{teacherId}")
    public Course assignHod(@PathVariable Long courseId,
                            @PathVariable Long teacherId){

        return service.assignOrUpdateHod(courseId,teacherId);
    }

    // ================= CREATE SUBJECT =================

    @PostMapping("/subjects")
    public Subject createSubject(@RequestBody Subject subject){

        return service.createSubject(subject);
    }

    // ================= CREATE MULTIPLE SUBJECTS =================

    @PostMapping("/subjects/bulk")
    public List<Subject> createSubjects(@RequestBody List<Subject> subjects){

        return service.createSubjects(subjects);
    }

    // ================= UPDATE SUBJECT =================

    @PutMapping("/subjects/{subjectId}")
    public Subject updateSubject(@PathVariable Long subjectId,
                                 @RequestBody Subject subject){

        return service.updateSubject(subjectId, subject);
    }

    // ================= DELETE SUBJECT =================

    @DeleteMapping("/subjects/{subjectId}")
    public String deleteSubject(@PathVariable Long subjectId){

        service.deleteSubject(subjectId);
        return "Subject deleted successfully";
    }

    // ================= GET SUBJECTS BY COURSE =================

    @GetMapping("/subjects/course/{courseId}")
    public List<Subject> getSubjects(@PathVariable Long courseId){

        return service.getSubjectsByCourse(courseId);
    }

    // ================= ASSIGN TEACHER =================

    @PostMapping("/assign-teacher")
    public String assignTeacher(@RequestParam Long teacherId,
                                @RequestParam Long subjectId,
                                @RequestParam int hours){

        return service.assignTeacherToSubject(teacherId,subjectId,hours);
    }

    // ================= REPLACE TEACHER =================

    @PutMapping("/replace-teacher")
    public String replaceTeacher(@RequestParam Long subjectId,
                                 @RequestParam Long newTeacherId,
                                 @RequestParam int hours){

        return service.replaceTeacher(subjectId,newTeacherId,hours);
    }

    // ================= TEACHER WORKLOAD =================

    @GetMapping("/teacher-workload/{teacherId}")
    public int getTeacherWorkload(@PathVariable Long teacherId){

        return service.getTeacherWorkload(teacherId);
    }

    // ================= SUBJECTS OF TEACHER =================

    @GetMapping("/teacher-subjects/{teacherId}")
    public List<TeacherSubject> getTeacherSubjects(@PathVariable Long teacherId){

        return service.getTeacherSubjects(teacherId);
    }
}