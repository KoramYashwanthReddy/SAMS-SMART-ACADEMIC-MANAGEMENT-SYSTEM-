package com.yashwanth.sem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long departmentId;

    private String name;

    private String code;

    private Long hodId;

    private String status;

    public Course() {}

    public Long getId() { return id; }

    public Long getDepartmentId() { return departmentId; }

    public void setDepartmentId(Long departmentId) { this.departmentId = departmentId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public Long getHodId() { return hodId; }

    public void setHodId(Long hodId) { this.hodId = hodId; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}