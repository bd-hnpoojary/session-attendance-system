package com.example.InternsAttendance.repository;

import com.example.InternsAttendance.model.AttendanceStatus;
import com.example.InternsAttendance.model.Intern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternRepository extends JpaRepository<Intern, Long> {
    List<Intern> findByAttendanceStatus(AttendanceStatus status);
}
