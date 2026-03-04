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

    @Column(name = "hod_id")
    private Long hodId;

    private String status;

    public Department() {}

    public Department(Long id, Long collegeId, String name, String code, Long hodId, String status) {
        this.id = id;
        this.collegeId = collegeId;
        this.name = name;
        this.code = code;
        this.hodId = hodId;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCollegeId() { return collegeId; }
    public void setCollegeId(Long collegeId) { this.collegeId = collegeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public Long getHodId() { return hodId; }
    public void setHodId(Long hodId) { this.hodId = hodId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}