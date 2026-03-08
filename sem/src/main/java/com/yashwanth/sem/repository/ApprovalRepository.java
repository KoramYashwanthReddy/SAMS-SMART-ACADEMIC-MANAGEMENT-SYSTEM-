package com.yashwanth.sem.repository;

import com.yashwanth.sem.entity.ApprovalRequest;
import com.yashwanth.sem.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovalRepository extends JpaRepository<ApprovalRequest, Long> {

    List<ApprovalRequest> findByApproved(boolean approved);

    List<ApprovalRequest> findByRequestedBy(User user);

    List<ApprovalRequest> findByApprovedBy(User user);

    List<ApprovalRequest> findByRequestType(String requestType);

}