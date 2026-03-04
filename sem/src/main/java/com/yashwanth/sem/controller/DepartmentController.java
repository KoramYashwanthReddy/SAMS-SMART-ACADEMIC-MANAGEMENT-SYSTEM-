package com.yashwanth.sem.controller;

import com.yashwanth.sem.dto.DepartmentDTO;
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

    @PostMapping
    public Department createDepartment(@RequestBody DepartmentDTO dto) {
        return departmentService.createDepartment(dto);
    }

    @GetMapping("/{collegeId}")
    public List<Department> getDepartments(@PathVariable Long collegeId) {
        return departmentService.getDepartments(collegeId);
    }

    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id,
                                       @RequestBody DepartmentDTO dto) {
        return departmentService.updateDepartment(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return "Department deleted successfully";
    }
}