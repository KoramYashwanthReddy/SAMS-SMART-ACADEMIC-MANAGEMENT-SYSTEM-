package com.yashwanth.sem.service;

import com.yashwanth.sem.entity.College;
import com.yashwanth.sem.enums.Role;
import org.springframework.stereotype.Service;

@Service
public class UserIdGeneratorService {

    public String generateUserId(Role role, College college, Long number) {

        String prefix;

        switch (role) {
            case SUPER_ADMIN:
                prefix = "SA";
                break;
            case COLLEGE_ADMIN:
                prefix = "CA";
                break;
            case PRINCIPAL:
                prefix = "PR";
                break;
            case TEACHER:
                prefix = "TR";
                break;
            case STUDENT:
                prefix = "ST";
                break;
            case EXAM_CONTROLLER:
                prefix = "EC";
                break;
            default:
                prefix = "US";
        }

        String collegeCode = college.getCollegeCode();

        return prefix + "-" + collegeCode + "-" + String.format("%04d", number);
    }
}