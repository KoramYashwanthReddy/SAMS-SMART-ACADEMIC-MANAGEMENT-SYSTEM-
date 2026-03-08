package com.yashwanth.sem.entity;

import jakarta.persistence.*;

@Entity
@Table(name="timetable")
public class TimeTable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String day;

    private int period;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private User teacher;

    @ManyToOne
    private Course course;

    public TimeTable(){}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id=id; }

    public String getDay(){ return day; }
    public void setDay(String day){ this.day=day; }

    public int getPeriod(){ return period; }
    public void setPeriod(int period){ this.period=period; }

    public Subject getSubject(){ return subject; }
    public void setSubject(Subject subject){ this.subject=subject; }

    public User getTeacher(){ return teacher; }
    public void setTeacher(User teacher){ this.teacher=teacher; }

    public Course getCourse(){ return course; }
    public void setCourse(Course course){ this.course=course; }
}