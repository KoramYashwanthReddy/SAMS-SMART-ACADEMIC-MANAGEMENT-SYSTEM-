package com.yashwanth.sem.service;

import com.yashwanth.sem.dto.CreateUserRequest;
import com.yashwanth.sem.dto.CourseDTO;
import com.yashwanth.sem.dto.DepartmentDTO;
import com.yashwanth.sem.entity.AcademicYear;
import com.yashwanth.sem.entity.College;
import com.yashwanth.sem.entity.Course;
import com.yashwanth.sem.entity.Department;
import com.yashwanth.sem.entity.User;
import com.yashwanth.sem.enums.Role;
import com.yashwanth.sem.repository.AcademicYearRepository;
import com.yashwanth.sem.repository.CollegeRepository;
import com.yashwanth.sem.repository.CourseRepository;
import com.yashwanth.sem.repository.DepartmentRepository;
import com.yashwanth.sem.repository.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class CollegeAdminService {

    private final UserRepository userRepository;
    private final CollegeRepository collegeRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;
    private final AcademicYearRepository academicYearRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserIdGeneratorService idGenerator;

    public CollegeAdminService(UserRepository userRepository,
                               CollegeRepository collegeRepository,
                               DepartmentRepository departmentRepository,
                               CourseRepository courseRepository,
                               AcademicYearRepository academicYearRepository,
                               PasswordEncoder passwordEncoder,
                               UserIdGeneratorService idGenerator) {

        this.userRepository = userRepository;
        this.collegeRepository = collegeRepository;
        this.departmentRepository = departmentRepository;
        this.courseRepository = courseRepository;
        this.academicYearRepository = academicYearRepository;
        this.passwordEncoder = passwordEncoder;
        this.idGenerator = idGenerator;
    }

    // ================= CREATE ROLE USERS =================

    public User createUser(CreateUserRequest request,
                           MultipartFile photo) throws Exception {

        if (userRepository.existsByUsername(request.getUsername()))
            throw new RuntimeException("Username already exists");

        if (userRepository.existsByEmail(request.getEmail()))
            throw new RuntimeException("Email already exists");

        College college = collegeRepository.findById(request.getCollegeId())
                .orElseThrow(() -> new RuntimeException("College not found"));

        Long count = userRepository.count() + 1;

        String systemId = idGenerator.generateUserId(
                request.getRole(),
                college,
                count
        );

        String fileName = System.currentTimeMillis() + "_" + photo.getOriginalFilename();

        Path uploadDir = Paths.get("uploads");

        if (!Files.exists(uploadDir))
            Files.createDirectories(uploadDir);

        Path filePath = uploadDir.resolve(fileName);

        Files.copy(photo.getInputStream(), filePath);

        User user = new User();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setDepartment(request.getDepartment());
        user.setCollegeUserId(request.getCollegeUserId());
        user.setSystemUserId(systemId);
        user.setProfilePhoto(filePath.toString());

        user.setRole(request.getRole());
        user.setCollege(college);
        user.setActive(true);

        return userRepository.save(user);
    }

    // ================= CREATE DEPARTMENT WITH COURSES =================

    public Department createDepartment(DepartmentDTO dto) {

        Department department = new Department();

        department.setCollegeId(dto.getCollegeId());
        department.setName(dto.getName());
        department.setCode(dto.getCode());
        department.setStatus("ACTIVE");

        Department savedDepartment = departmentRepository.save(department);

        // ================= CREATE COURSES =================

        if (dto.getCourses() != null && !dto.getCourses().isEmpty()) {

            for (CourseDTO courseDTO : dto.getCourses()) {

                if (courseDTO.getDurationYears() <= 0) {
                    throw new RuntimeException("Course duration must be greater than 0");
                }

                Course course = new Course();

                course.setDepartmentId(savedDepartment.getId());
                course.setName(courseDTO.getName());
                course.setCode(courseDTO.getCode());
                course.setDurationYears(courseDTO.getDurationYears());
                course.setStatus("ACTIVE");

                Course savedCourse = courseRepository.save(course);

                // ================= AUTO CREATE ACADEMIC YEARS =================

                for (int i = 1; i <= savedCourse.getDurationYears(); i++) {

                    AcademicYear year = new AcademicYear();
                    year.setCourseId(savedCourse.getId());
                    year.setYearNumber(i);

                    academicYearRepository.save(year);
                }
            }
        }

        return savedDepartment;
    }

    // ================= GET DEPARTMENTS =================

    public List<Department> getDepartments(Long collegeId) {
        return departmentRepository.findByCollegeId(collegeId);
    }

    // ================= ASSIGN DEPARTMENT ADMIN =================

    public Department assignDepartmentAdmin(Long departmentId, Long adminId) {

        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        User admin = userRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (admin.getRole() != Role.DEPARTMENT_ADMIN)
            throw new RuntimeException("User is not a Department Admin");

        department.setDeptAdminId(adminId);

        return departmentRepository.save(department);
    }

    // ================= GET USERS BY COLLEGE =================

    public List<User> getUsersByCollege(Long collegeId) {
        return userRepository.findByCollegeId(collegeId);
    }

    // ================= GET USERS BY ROLE =================

    public List<User> getUsersByRole(Long collegeId, Role role) {
        return userRepository.findByCollegeIdAndRole(collegeId, role);
    }

    // ================= UPDATE USER =================

    public User updateUser(Long id, CreateUserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        return userRepository.save(user);
    }

    // ================= DEACTIVATE USER =================

    public void deactivateUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setActive(false);

        userRepository.save(user);
    }
}