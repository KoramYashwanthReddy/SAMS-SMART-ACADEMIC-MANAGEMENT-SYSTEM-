package com.yashwanth.sem.service;

import com.yashwanth.sem.dto.DepartmentDTO;
import com.yashwanth.sem.entity.Department;
import com.yashwanth.sem.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department createDepartment(DepartmentDTO dto) {

        Department department = new Department();

        department.setCollegeId(dto.getCollegeId());
        department.setName(dto.getName());
        department.setCode(dto.getCode());
        department.setHodId(dto.getHodId());
        department.setStatus("ACTIVE");

        return departmentRepository.save(department);
    }

    public List<Department> getDepartments(Long collegeId) {
        return departmentRepository.findByCollegeId(collegeId);
    }

    public Department updateDepartment(Long id, DepartmentDTO dto) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        department.setName(dto.getName());
        department.setCode(dto.getCode());
        department.setHodId(dto.getHodId());

        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}