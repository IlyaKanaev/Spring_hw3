// 3.1 Создать класс Project с полями id, name
package com.example.timesheet.model;

import lombok.Data;

@Data
public class Project {

    private Long id;
    private String projectName;
}
