package com.yashwanth.sem.entity;

import jakarta.persistence.*;

@Entity
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Column(unique=true)
    private String rollNumber;

    private String email;
    private String phone;

    private int year;

    private boolean active;

    private boolean approved;

    @ManyToOne
    private Course course;

    @ManyToOne
    private AcademicYear academicYear;

    public Student(){}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id=id; }

    public String getName(){ return name; }
    public void setName(String name){ this.name=name; }

    public String getRollNumber(){ return rollNumber; }
    public void setRollNumber(String rollNumber){ this.rollNumber=rollNumber; }

    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email=email; }

    public String getPhone(){ return phone; }
    public void setPhone(String phone){ this.phone=phone; }

    public int getYear(){ return year; }
    public void setYear(int year){ this.year=year; }

    public boolean isActive(){ return active; }
    public void setActive(boolean active){ this.active=active; }

    public boolean isApproved(){ return approved; }
    public void setApproved(boolean approved){ this.approved=approved; }

    public Course getCourse(){ return course; }
    public void setCourse(Course course){ this.course=course; }

    public AcademicYear getAcademicYear(){ return academicYear; }
    public void setAcademicYear(AcademicYear academicYear){ this.academicYear=academicYear; }
}