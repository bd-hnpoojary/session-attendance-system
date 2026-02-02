package com.example.InternsAttendance.controller;

import com.example.InternsAttendance.dto.InternRequest;
import com.example.InternsAttendance.dto.InternResponse;
import com.example.InternsAttendance.model.AttendanceStatus;
import com.example.InternsAttendance.model.Intern;
import com.example.InternsAttendance.service.InternService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/")
public class InternController {

    private static final Logger logger = LoggerFactory.getLogger(InternController.class);

    private final InternService internService;

    public InternController(InternService internService) {
        this.internService = internService;
    }

    @PostMapping("/interns/add")
    public ResponseEntity<InternResponse> createIntern(@Valid @RequestBody InternRequest request) {
        logger.info("Received request to create intern: {}", request.getName());
        Intern intern = Intern.builder()
                .name(request.getName())
                .team(request.getTeam())
                .attendanceStatus(request.getAttendanceStatus())
                .role(request.getRole())
                .build();

        Intern created = internService.createIntern(intern);
        InternResponse response = mapToResponse(created);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<InternResponse>> getAllInterns() {
        logger.info("Fetching all interns");
        List<InternResponse> response = internService.getAllInterns().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/interns/present")
    public ResponseEntity<List<InternResponse>> getPresentInterns() {
        logger.info("Fetching present interns");
        List<InternResponse> response = internService.getInternsByStatus(AttendanceStatus.PRESENT).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/interns/absent")
    public ResponseEntity<List<InternResponse>> getAbsentInterns() {
        logger.info("Fetching absent interns");
        List<InternResponse> response = internService.getInternsByStatus(AttendanceStatus.ABSENT).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    private InternResponse mapToResponse(Intern intern) {
        return InternResponse.builder()
                .id(intern.getId())
                .name(intern.getName())
                .team(intern.getTeam())
                .attendanceStatus(intern.getAttendanceStatus())
                .role(intern.getRole())
                .build();
    }
}
