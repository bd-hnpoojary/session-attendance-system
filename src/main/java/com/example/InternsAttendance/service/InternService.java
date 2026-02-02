package com.example.InternsAttendance.service;

import com.example.InternsAttendance.model.AttendanceStatus;
import com.example.InternsAttendance.model.Intern;

import java.util.List;

public interface InternService {
    Intern createIntern(Intern intern);
    List<Intern> getAllInterns();
    List<Intern> getInternsByStatus(AttendanceStatus status);
}
