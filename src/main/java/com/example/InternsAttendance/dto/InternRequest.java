package com.example.InternsAttendance.dto;

import com.example.InternsAttendance.model.AttendanceStatus;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InternRequest {

    @NotBlank
    private String name;

    private String team;

    private AttendanceStatus attendanceStatus;

    private String role;
}
