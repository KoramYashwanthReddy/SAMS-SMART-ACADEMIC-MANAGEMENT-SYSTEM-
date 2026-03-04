package com.yashwanth.sem.dto;

public class DepartmentDTO {

    private Long collegeId;
    private String name;
    private String code;
    private Long hodId;

    public DepartmentDTO() {}

    public Long getCollegeId() { return collegeId; }
    public void setCollegeId(Long collegeId) { this.collegeId = collegeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public Long getHodId() { return hodId; }
    public void setHodId(Long hodId) { this.hodId = hodId; }
}