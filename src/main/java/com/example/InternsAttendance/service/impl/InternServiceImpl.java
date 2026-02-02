package com.example.InternsAttendance.service.impl;

import com.example.InternsAttendance.model.AttendanceStatus;
import com.example.InternsAttendance.model.Intern;
import com.example.InternsAttendance.repository.InternRepository;
import com.example.InternsAttendance.service.InternService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InternServiceImpl implements InternService {

    private static final Logger logger = LoggerFactory.getLogger(InternServiceImpl.class);

    private final InternRepository internRepository;

    public InternServiceImpl(InternRepository internRepository) {
        this.internRepository = internRepository;
    }

    @Override
    public Intern createIntern(Intern intern) {
        if (intern.getRole() == null || intern.getRole().isBlank()) {
            intern.setRole("intern");
        }
        logger.info("Creating intern: {}", intern.getName());
        return internRepository.save(intern);
    }

    @Override
    public List<Intern> getAllInterns() {
        logger.debug("Fetching all interns");
        return internRepository.findAll();
    }

    @Override
    public List<Intern> getInternsByStatus(AttendanceStatus status) {
        logger.debug("Fetching interns by status: {}", status);
        return internRepository.findByAttendanceStatus(status);
    }
}
