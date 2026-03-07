package com.yashwanth.sem.controller;

import com.yashwanth.sem.entity.User;
import com.yashwanth.sem.repository.UserRepository;
import com.yashwanth.sem.service.IdCardPdfService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/idcard")
public class IdCardController {

    private final UserRepository userRepository;
    private final IdCardPdfService pdfService;

    public IdCardController(UserRepository userRepository,
                            IdCardPdfService pdfService) {
        this.userRepository = userRepository;
        this.pdfService = pdfService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<byte[]> generateIdCard(@PathVariable Long userId) throws Exception {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        byte[] pdf = pdfService.generateIdCard(user);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=idcard.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}