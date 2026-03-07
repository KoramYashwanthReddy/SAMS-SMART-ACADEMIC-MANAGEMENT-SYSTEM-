package com.yashwanth.sem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long courseId;

    private String name;

    private String code;

    private int hoursPerWeek;

    public Subject(){}

    public Long getId(){ return id; }

    public Long getCourseId(){ return courseId; }

    public void setCourseId(Long courseId){ this.courseId = courseId; }

    public String getName(){ return name; }

    public void setName(String name){ this.name = name; }

    public String getCode(){ return code; }

    public void setCode(String code){ this.code = code; }

    public int getHoursPerWeek(){ return hoursPerWeek; }

    public void setHoursPerWeek(int hoursPerWeek){ this.hoursPerWeek = hoursPerWeek; }
}