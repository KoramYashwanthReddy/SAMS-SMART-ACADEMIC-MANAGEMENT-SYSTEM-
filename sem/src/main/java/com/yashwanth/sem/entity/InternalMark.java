package com.yashwanth.sem.entity;

import jakarta.persistence.*;

@Entity
@Table(name="internal_marks")
public class InternalMark {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private int internal1;
    private int internal2;
    private int internal3;

    private double average;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Subject subject;

    public InternalMark(){}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id=id; }

    public int getInternal1(){ return internal1; }
    public void setInternal1(int internal1){ this.internal1=internal1; }

    public int getInternal2(){ return internal2; }
    public void setInternal2(int internal2){ this.internal2=internal2; }

    public int getInternal3(){ return internal3; }
    public void setInternal3(int internal3){ this.internal3=internal3; }

    public double getAverage(){ return average; }
    public void setAverage(double average){ this.average=average; }

    public Student getStudent(){ return student; }
    public void setStudent(Student student){ this.student=student; }

    public Subject getSubject(){ return subject; }
    public void setSubject(Subject subject){ this.subject=subject; }
}