package com.yashwanth.sem.service;

import com.yashwanth.sem.entity.Department;
import com.yashwanth.sem.entity.User;
import com.yashwanth.sem.enums.Role;
import com.yashwanth.sem.repository.DepartmentRepository;
import com.yashwanth.sem.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    public DepartmentService(DepartmentRepository departmentRepository,
                             UserRepository userRepository) {
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
    }

    // ================= GET DEPARTMENTS =================

    public List<Department> getDepartments(Long collegeId) {

        return departmentRepository.findByCollegeId(collegeId);
    }

    // ================= UPDATE =================

    public Department updateDepartment(Long id, String name, String code) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        if (name != null) {
            department.setName(name);
        }

        if (code != null) {
            department.setCode(code);
        }

        return departmentRepository.save(department);
    }

    // ================= ASSIGN DEPARTMENT ADMIN =================

    public Department assignDepartmentAdmin(Long departmentId, Long adminId) {

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (admin.getRole() != Role.DEPARTMENT_ADMIN) {
            throw new RuntimeException("User is not a Department Admin");
        }

        department.setDeptAdminId(adminId);

        return departmentRepository.save(department);
    }

    // ================= DELETE =================

    public void deleteDepartment(Long id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        departmentRepository.delete(department);
    }
}