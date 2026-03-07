package com.yashwanth.sem.entity;

import jakarta.persistence.*;

@Entity
@Table(name="teacher_subjects")
public class TeacherSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long teacherId;

    private Long subjectId;

    private int hours;

    public TeacherSubject(){}

    public Long getTeacherId(){ return teacherId; }

    public void setTeacherId(Long teacherId){ this.teacherId = teacherId; }

    public Long getSubjectId(){ return subjectId; }

    public void setSubjectId(Long subjectId){ this.subjectId = subjectId; }

    public int getHours(){ return hours; }

    public void setHours(int hours){ this.hours = hours; }
}