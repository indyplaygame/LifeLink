package dev.indy.lifelink.controller;

import dev.indy.lifelink.auth.AuthRequired;
import dev.indy.lifelink.decorators.pagination.Paginated;
import dev.indy.lifelink.exception.EntityNotFoundException;
import dev.indy.lifelink.exception.HttpException;
import dev.indy.lifelink.model.MedicineSchedule;
import dev.indy.lifelink.model.request.AddMedicineScheduleRequest;
import dev.indy.lifelink.model.response.PageResponse;
import dev.indy.lifelink.service.SchedulerService;
import dev.indy.lifelink.validation.ValidationGroups;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
public class SchedulerController {
    private final SchedulerService _schedulerService;

    @Autowired
    public SchedulerController(SchedulerService schedulerService) {
        this._schedulerService = schedulerService;
    }

    @AuthRequired
    @PostMapping("/add")
    public ResponseEntity<MedicineSchedule> add(
        @Validated(ValidationGroups.OnCreate.class) @RequestBody AddMedicineScheduleRequest body, HttpSession session
    ) {
        try {
            MedicineSchedule schedule = this._schedulerService.addSchedule(session, body);
            return ResponseEntity.status(HttpStatus.CREATED).body(schedule);
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @AuthRequired
    @GetMapping("/{id}")
    public ResponseEntity<MedicineSchedule> get(@PathVariable Long id, HttpSession session) {
        try {
            MedicineSchedule schedule = this._schedulerService.getSchedule(session, id);
            return ResponseEntity.ok(schedule);
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @AuthRequired
    @PutMapping("/{id}/update")
    public ResponseEntity<MedicineSchedule> update(
        @PathVariable Long id,
        @Validated(ValidationGroups.OnUpdate.class) @RequestBody AddMedicineScheduleRequest body,
        HttpSession session
    ) {
        try {
            MedicineSchedule schedule = this._schedulerService.updateSchedule(session, id, body);
            return ResponseEntity.ok(schedule);
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @AuthRequired
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id, HttpSession session) {
        try {
            this._schedulerService.deleteSchedule(session, id);
            return ResponseEntity.noContent().build();
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Paginated(defaultSize = 20)
    @AuthRequired
    @GetMapping("/list")
    public ResponseEntity<PageResponse<MedicineSchedule>> list(Pageable pageable, HttpSession session) {
        return ResponseEntity.ok(PageResponse.from(this._schedulerService.listPatientSchedules(session, pageable)));
    }
}
