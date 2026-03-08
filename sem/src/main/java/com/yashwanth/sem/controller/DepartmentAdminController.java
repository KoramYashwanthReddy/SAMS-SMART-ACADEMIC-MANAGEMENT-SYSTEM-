package com.yashwanth.sem.controller;

import com.yashwanth.sem.entity.AcademicYear;
import com.yashwanth.sem.entity.Course;
import com.yashwanth.sem.entity.Subject;
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

        return service.assignOrUpdateHod(courseId, teacherId);
    }

    // ================= UPDATE CLASS NAME =================

    @PutMapping("/academic-years/{id}/class")
    public AcademicYear updateClassName(@PathVariable Long id,
                                        @RequestParam String className){

        return service.updateClassName(id, className);
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

    // ================= GET SUBJECTS BY ACADEMIC YEAR =================

    @GetMapping("/subjects/year/{academicYearId}")
    public List<Subject> getSubjects(@PathVariable Long academicYearId){

        return service.getSubjectsByAcademicYear(academicYearId);
    }
}