package com.yashwanth.sem.dto;

public class SuperAdminDashboardDTO {

    private long totalColleges;
    private long pendingColleges;
    private long approvedColleges;
    private long blockedColleges;
    private long rejectedColleges;

    private long totalUsers;
    private long totalCollegeAdmins;

    public SuperAdminDashboardDTO() {}

    public SuperAdminDashboardDTO(long totalColleges,
                                  long pendingColleges,
                                  long approvedColleges,
                                  long blockedColleges,
                                  long rejectedColleges,
                                  long totalUsers,
                                  long totalCollegeAdmins) {

        this.totalColleges = totalColleges;
        this.pendingColleges = pendingColleges;
        this.approvedColleges = approvedColleges;
        this.blockedColleges = blockedColleges;
        this.rejectedColleges = rejectedColleges;
        this.totalUsers = totalUsers;
        this.totalCollegeAdmins = totalCollegeAdmins;
    }

    public long getTotalColleges() {
        return totalColleges;
    }

    public long getPendingColleges() {
        return pendingColleges;
    }

    public long getApprovedColleges() {
        return approvedColleges;
    }

    public long getBlockedColleges() {
        return blockedColleges;
    }

    public long getRejectedColleges() {
        return rejectedColleges;
    }

    public long getTotalUsers() {
        return totalUsers;
    }

    public long getTotalCollegeAdmins() {
        return totalCollegeAdmins;
    }
}