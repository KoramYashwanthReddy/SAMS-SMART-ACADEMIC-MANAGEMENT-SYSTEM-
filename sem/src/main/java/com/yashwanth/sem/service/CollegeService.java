package com.yashwanth.sem.service;

import com.yashwanth.sem.dto.SuperAdminDashboardDTO;
import com.yashwanth.sem.entity.College;

import java.util.List;

public interface CollegeService {

    College createCollege(College college);

    List<College> getAllColleges();

    College getCollegeById(Long id);

    College updateCollege(Long id, College college);

    void deleteCollege(Long id);

    // Approval workflow
    College approveCollege(Long id);

    College rejectCollege(Long id);

    College blockCollege(Long id);

    College unblockCollege(Long id);

    List<College> getPendingColleges();

    // Dashboard
    SuperAdminDashboardDTO getDashboard();
}