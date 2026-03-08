package com.yashwanth.sem.entity;

import jakarta.persistence.*;

@Entity
@Table(name="results")
public class Result {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private int marks;

    private boolean passStatus;

    private boolean published;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Exam exam;

    public Result(){}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id=id; }

    public int getMarks(){ return marks; }
    public void setMarks(int marks){ this.marks=marks; }

    public boolean isPassStatus(){ return passStatus; }
    public void setPassStatus(boolean passStatus){ this.passStatus=passStatus; }

    public boolean isPublished(){ return published; }
    public void setPublished(boolean published){ this.published=published; }

    public Student getStudent(){ return student; }
    public void setStudent(Student student){ this.student=student; }

    public Exam getExam(){ return exam; }
    public void setExam(Exam exam){ this.exam=exam; }
}