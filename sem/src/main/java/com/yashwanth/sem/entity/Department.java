package com.yashwanth.sem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "college_id", nullable = false)
    private Long collegeId;

    @Column(nullable = false)
    private String name;

    @Column(unique = true)
    private String code;

    @Column(name = "dept_admin_id")
    private Long deptAdminId;

    @Column(name = "hod_id")
    private Long hodId;

    private String status;

    public Department() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCollegeId() { return collegeId; }
    public void setCollegeId(Long collegeId) { this.collegeId = collegeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public Long getDeptAdminId() { return deptAdminId; }
    public void setDeptAdminId(Long deptAdminId) { this.deptAdminId = deptAdminId; }

    public Long getHodId() { return hodId; }
    public void setHodId(Long hodId) { this.hodId = hodId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}