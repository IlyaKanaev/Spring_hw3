package com.example.timesheet.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Timesheet {
    private Long id;
    // 3.3 В классе (ресурсе) Timesheet поле project заменить на projectId
    private String projectId;
    private int minutes;
    private LocalDate createdAt;
}
