package com.yashwanth.sem.service;

import com.yashwanth.sem.dto.CreateCollegeAdminRequest;
import com.yashwanth.sem.entity.User;

public interface UserService {

    User createCollegeAdmin(CreateCollegeAdminRequest request);
}