package com.yashwanth.sem.config;

import com.yashwanth.sem.entity.User;
import com.yashwanth.sem.enums.Role;
import com.yashwanth.sem.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Configuration
public class DefaultSuperAdminInitializer {

    @Bean
    CommandLineRunner createDefaultSuperAdmin(UserRepository userRepository,
                                              PasswordEncoder passwordEncoder) {

        return args -> {

            if (!userRepository.existsByRole(Role.SUPER_ADMIN)) {

                User admin = new User();

                admin.setFirstName("Super");
                admin.setLastName("Admin");
                admin.setEmail("superadmin@sams.com");
                admin.setUsername("superadmin");

                admin.setPassword(passwordEncoder.encode("Admin@123"));

                admin.setRole(Role.SUPER_ADMIN);
                admin.setActive(true);

                // REQUIRED FIELDS
                admin.setCollegeUserId("SYS-ADMIN-001");

                admin.setSystemUserId("SYS-" + UUID.randomUUID().toString().substring(0,8));

                admin.setDepartment("SYSTEM");

                userRepository.save(admin);

                System.out.println("✅ Default Super Admin Created");
            }
        };
    }
}