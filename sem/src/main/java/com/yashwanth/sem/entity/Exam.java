package com.yashwanth.sem.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="exams")
public class Exam {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String examName;

    private LocalDate examDate;

    private int totalMarks;

    private boolean approved;

    private boolean published;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Course course;

    @ManyToOne
    private AcademicYear academicYear;

    public Exam(){}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id=id; }

    public String getExamName(){ return examName; }
    public void setExamName(String examName){ this.examName=examName; }

    public LocalDate getExamDate(){ return examDate; }
    public void setExamDate(LocalDate examDate){ this.examDate=examDate; }

    public int getTotalMarks(){ return totalMarks; }
    public void setTotalMarks(int totalMarks){ this.totalMarks=totalMarks; }

    public boolean isApproved(){ return approved; }
    public void setApproved(boolean approved){ this.approved=approved; }

    public boolean isPublished(){ return published; }
    public void setPublished(boolean published){ this.published=published; }

    public Subject getSubject(){ return subject; }
    public void setSubject(Subject subject){ this.subject=subject; }

    public Course getCourse(){ return course; }
    public void setCourse(Course course){ this.course=course; }

    public AcademicYear getAcademicYear(){ return academicYear; }
    public void setAcademicYear(AcademicYear academicYear){ this.academicYear=academicYear; }
}