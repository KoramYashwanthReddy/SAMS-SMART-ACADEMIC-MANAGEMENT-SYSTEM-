package com.yashwanth.sem.entity;

import com.yashwanth.sem.enums.CollegeStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "colleges")
public class College {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String collegeName;

    @Column(unique = true)
    private String collegeCode;

    private String email;

    private String phone;

    private String address;

    @Enumerated(EnumType.STRING)
    private CollegeStatus status = CollegeStatus.PENDING;

    public College() {}

    public Long getId() {
        return id;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCollegeCode() {
        return collegeCode;
    }

    public void setCollegeCode(String collegeCode) {
        this.collegeCode = collegeCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CollegeStatus getStatus() {
        return status;
    }

    public void setStatus(CollegeStatus status) {
        this.status = status;
    }
}