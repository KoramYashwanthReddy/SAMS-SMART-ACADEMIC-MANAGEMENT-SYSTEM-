package com.yashwanth.sem.controller;

import com.yashwanth.sem.entity.Department;
import com.yashwanth.sem.service.DepartmentService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    // ================= GET BY COLLEGE =================

    @GetMapping("/college/{collegeId}")
    public List<Department> getDepartments(@PathVariable Long collegeId) {

        return departmentService.getDepartments(collegeId);
    }

    // ================= UPDATE =================

    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id,
                                       @RequestParam(required = false) String name,
                                       @RequestParam(required = false) String code) {

        return departmentService.updateDepartment(id, name, code);
    }

    // ================= ASSIGN DEPT ADMIN =================

    @PutMapping("/{departmentId}/assign-admin/{adminId}")
    public Department assignDepartmentAdmin(
            @PathVariable Long departmentId,
            @PathVariable Long adminId) {

        return departmentService.assignDepartmentAdmin(departmentId, adminId);
    }

    // ================= DELETE =================

    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id) {

        departmentService.deleteDepartment(id);

        return "Department deleted successfully";
    }
}