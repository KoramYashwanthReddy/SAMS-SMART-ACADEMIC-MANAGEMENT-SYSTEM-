package com.yashwanth.sem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "college_admins")
public class CollegeAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="college_id", nullable = false)
    private Long collegeId;

    private String name;

    @Column(unique = true)
    private String email;

    private String phone;

    private String password;

    private String status;

    public CollegeAdmin() {}

    public CollegeAdmin(Long id, Long collegeId, String name, String email,
                        String phone, String password, String status) {
        this.id = id;
        this.collegeId = collegeId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCollegeId() { return collegeId; }
    public void setCollegeId(Long collegeId) { this.collegeId = collegeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}