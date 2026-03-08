package com.yashwanth.sem.dto;

public class HodDashboardDTO {

    private long totalStudents;
    private long totalExams;

    public HodDashboardDTO() {}

    public long getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(long totalStudents) {
        this.totalStudents = totalStudents;
    }

    public long getTotalExams() {
        return totalExams;
    }

    public void setTotalExams(long totalExams) {
        this.totalExams = totalExams;
    }
}