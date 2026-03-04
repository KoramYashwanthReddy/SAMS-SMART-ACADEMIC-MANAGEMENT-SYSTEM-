package com.yashwanth.sem.service;

import com.yashwanth.sem.dto.SuperAdminDashboardDTO;
import com.yashwanth.sem.entity.College;
import com.yashwanth.sem.enums.CollegeStatus;
import com.yashwanth.sem.enums.Role;
import com.yashwanth.sem.exception.ResourceNotFoundException;
import com.yashwanth.sem.repository.CollegeRepository;
import com.yashwanth.sem.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuditService auditService;

    @Override
    public College createCollege(College college) {

        college.setStatus(CollegeStatus.PENDING);

        College savedCollege = collegeRepository.save(college);

        auditService.log("CREATE_COLLEGE", "SUPER_ADMIN", "College", savedCollege.getId());

        return savedCollege;
    }

    @Override
    public List<College> getAllColleges() {
        return collegeRepository.findAll();
    }

    @Override
    public College getCollegeById(Long id) {

        return collegeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("College not found"));
    }

    @Override
    public College updateCollege(Long id, College college) {

        College existing = getCollegeById(id);

        existing.setCollegeName(college.getCollegeName());
        existing.setCollegeCode(college.getCollegeCode());
        existing.setEmail(college.getEmail());
        existing.setPhone(college.getPhone());
        existing.setAddress(college.getAddress());

        College updatedCollege = collegeRepository.save(existing);

        auditService.log("UPDATE_COLLEGE", "SUPER_ADMIN", "College", id);

        return updatedCollege;
    }

    @Override
    public void deleteCollege(Long id) {

        College college = getCollegeById(id);

        collegeRepository.delete(college);

        auditService.log("DELETE_COLLEGE", "SUPER_ADMIN", "College", id);
    }

    @Override
    public College approveCollege(Long id) {

        College college = getCollegeById(id);

        college.setStatus(CollegeStatus.APPROVED);

        College savedCollege = collegeRepository.save(college);

        auditService.log("APPROVE_COLLEGE", "SUPER_ADMIN", "College", id);

        return savedCollege;
    }

    @Override
    public College rejectCollege(Long id) {

        College college = getCollegeById(id);

        college.setStatus(CollegeStatus.REJECTED);

        College savedCollege = collegeRepository.save(college);

        auditService.log("REJECT_COLLEGE", "SUPER_ADMIN", "College", id);

        return savedCollege;
    }

    @Override
    public College blockCollege(Long id) {

        College college = getCollegeById(id);

        college.setStatus(CollegeStatus.BLOCKED);

        College savedCollege = collegeRepository.save(college);

        auditService.log("BLOCK_COLLEGE", "SUPER_ADMIN", "College", id);

        return savedCollege;
    }

    @Override
    public College unblockCollege(Long id) {

        College college = getCollegeById(id);

        college.setStatus(CollegeStatus.APPROVED);

        College savedCollege = collegeRepository.save(college);

        auditService.log("UNBLOCK_COLLEGE", "SUPER_ADMIN", "College", id);

        return savedCollege;
    }

    @Override
    public List<College> getPendingColleges() {

        return collegeRepository.findByStatus(CollegeStatus.PENDING);
    }

    @Override
    public SuperAdminDashboardDTO getDashboard() {

        long totalColleges = collegeRepository.count();

        long pendingColleges = collegeRepository.countByStatus(CollegeStatus.PENDING);
        long approvedColleges = collegeRepository.countByStatus(CollegeStatus.APPROVED);
        long blockedColleges = collegeRepository.countByStatus(CollegeStatus.BLOCKED);
        long rejectedColleges = collegeRepository.countByStatus(CollegeStatus.REJECTED);

        long totalUsers = userRepository.count();
        long totalCollegeAdmins = userRepository.countByRole(Role.COLLEGE_ADMIN);

        return new SuperAdminDashboardDTO(
                totalColleges,
                pendingColleges,
                approvedColleges,
                blockedColleges,
                rejectedColleges,
                totalUsers,
                totalCollegeAdmins
        );
    }
}