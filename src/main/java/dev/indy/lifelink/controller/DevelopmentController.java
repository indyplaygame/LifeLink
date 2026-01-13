package dev.indy.lifelink.controller;

import dev.indy.lifelink.model.MedicineScheduleLog;
import dev.indy.lifelink.model.response.ScheduledTaskResponse;
import dev.indy.lifelink.service.DevelopmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Profile({"dev", "stage"})
@RestController
@RequestMapping("/dev")
public class DevelopmentController {
    private final DevelopmentService _developmentService;

    @Autowired
    public DevelopmentController(DevelopmentService developmentService) {
        this._developmentService = developmentService;
    }

    @GetMapping("/scheduler/tasks")
    public ResponseEntity<List<ScheduledTaskResponse>> listScheduledTasks() {
        List<ScheduledTaskResponse> scheduledTasks = this._developmentService.listScheduledTasks();
        return ResponseEntity.ok(scheduledTasks);
    }

    @DeleteMapping("/db/clear")
    public ResponseEntity<Void> clearDatabase() {
        this._developmentService.clearDatabase();
        return ResponseEntity.noContent().build();
    }
}
