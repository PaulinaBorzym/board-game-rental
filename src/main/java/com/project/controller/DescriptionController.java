package com.project.controller;

import com.project.service.DescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/description")
public class DescriptionController {
    private final DescriptionService service;

    @GetMapping
    public ResponseEntity<String> getDescription(
            @RequestParam(value = "language") String language,
            @RequestParam(value = "description") String description) throws IOException {
        String result = service.getDescription(language, description);
        return ResponseEntity.ok(result);
    }
}
