package com.example.timesheet.controller;

import com.example.timesheet.model.Timesheet;
import com.example.timesheet.service.TimesheetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/timesheet")
public class TimesheetController {

    private final TimesheetService service;
    private final ProjectController projectController;

    public TimesheetController(TimesheetService service, ProjectController projectController) {
        this.service = service;
        this.projectController = projectController;
    }

    @GetMapping("{id}") // получить конкретную запись по идентификатору
    public ResponseEntity<Timesheet> get(@PathVariable Long id) {
        Optional<Timesheet> ts = service.getById(id);

        if (ts.isPresent()) {
            // return ResponseEntity.ok().body(ts.get());
            return ResponseEntity.status(HttpStatus.OK).body(ts.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping // получить все
    public ResponseEntity<List<Timesheet>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping// создание нового ресурса
    public ResponseEntity<Timesheet> create(@RequestBody Timesheet timesheet, @PathVariable("projectId") Long projectId) {
        if (projectController.getProjectById(projectId).getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            timesheet.setProjectId(String.valueOf(projectId));
            timesheet = service.create(timesheet);
            return ResponseEntity.status(HttpStatus.CREATED).body(timesheet);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        // 204 No Content
        return ResponseEntity.noContent().build();
    }
}
