package com.yashwanth.sem.controller;

import com.yashwanth.sem.dto.CreateCollegeAdminRequest;
import com.yashwanth.sem.dto.SuperAdminDashboardDTO;
import com.yashwanth.sem.entity.AuditLog;
import com.yashwanth.sem.entity.College;
import com.yashwanth.sem.entity.User;
import com.yashwanth.sem.service.AuditService;
import com.yashwanth.sem.service.CollegeService;
import com.yashwanth.sem.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/superadmin")
public class SuperAdminController {

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuditService auditService;

    // ================= DASHBOARD =================

    @GetMapping("/dashboard")
    public ResponseEntity<SuperAdminDashboardDTO> getDashboard() {

        return ResponseEntity.ok(collegeService.getDashboard());
    }

    // ================= AUDIT LOGS =================

    @GetMapping("/audit-logs")
    public ResponseEntity<List<AuditLog>> getAuditLogs() {

        return ResponseEntity.ok(auditService.getRecentLogs());
    }

    // ================= COLLEGE MANAGEMENT =================

    @PostMapping("/colleges")
    public ResponseEntity<College> createCollege(@RequestBody College college) {

        return ResponseEntity.ok(collegeService.createCollege(college));
    }

    @GetMapping("/colleges")
    public ResponseEntity<List<College>> getAllColleges() {

        return ResponseEntity.ok(collegeService.getAllColleges());
    }

    @GetMapping("/colleges/{id}")
    public ResponseEntity<College> getCollege(@PathVariable Long id) {

        return ResponseEntity.ok(collegeService.getCollegeById(id));
    }

    @PutMapping("/colleges/{id}")
    public ResponseEntity<College> updateCollege(
            @PathVariable Long id,
            @RequestBody College college) {

        return ResponseEntity.ok(collegeService.updateCollege(id, college));
    }

    @DeleteMapping("/colleges/{id}")
    public ResponseEntity<String> deleteCollege(@PathVariable Long id) {

        collegeService.deleteCollege(id);
        return ResponseEntity.ok("College deleted successfully");
    }

    // ================= APPROVAL WORKFLOW =================

    @GetMapping("/colleges/pending")
    public ResponseEntity<List<College>> getPendingColleges() {

        return ResponseEntity.ok(collegeService.getPendingColleges());
    }

    @PutMapping("/colleges/{id}/approve")
    public ResponseEntity<College> approveCollege(@PathVariable Long id) {

        return ResponseEntity.ok(collegeService.approveCollege(id));
    }

    @PutMapping("/colleges/{id}/reject")
    public ResponseEntity<College> rejectCollege(@PathVariable Long id) {

        return ResponseEntity.ok(collegeService.rejectCollege(id));
    }

    @PutMapping("/colleges/{id}/block")
    public ResponseEntity<College> blockCollege(@PathVariable Long id) {

        return ResponseEntity.ok(collegeService.blockCollege(id));
    }

    @PutMapping("/colleges/{id}/unblock")
    public ResponseEntity<College> unblockCollege(@PathVariable Long id) {

        return ResponseEntity.ok(collegeService.unblockCollege(id));
    }

    // ================= CREATE COLLEGE ADMIN =================

    @PostMapping("/college-admin")
    public ResponseEntity<User> createCollegeAdmin(
            @RequestBody CreateCollegeAdminRequest request) {

        return ResponseEntity.ok(userService.createCollegeAdmin(request));
    }
}