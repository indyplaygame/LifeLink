package dev.indy.lifelink.controller;

import dev.indy.lifelink.auth.AuthRequired;
import dev.indy.lifelink.decorators.pagination.Paginated;
import dev.indy.lifelink.exception.EntityNotFoundException;
import dev.indy.lifelink.exception.HttpException;
import dev.indy.lifelink.model.ChronicDisease;
import dev.indy.lifelink.model.Medicine;
import dev.indy.lifelink.model.request.AddMedicineRequest;
import dev.indy.lifelink.model.response.PageResponse;
import dev.indy.lifelink.service.MedicineService;
import dev.indy.lifelink.validation.ValidationGroups;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicines")
public class MedicineController {
    private final MedicineService _medicineService;

    @Autowired
    public MedicineController(MedicineService medicineService) {
        this._medicineService = medicineService;
    }

    @AuthRequired
    @PostMapping(value = "/add")
    public ResponseEntity<Medicine> add(
            @Validated(ValidationGroups.OnCreate.class) @RequestBody AddMedicineRequest body, HttpSession session
    ) {
        Medicine medicine = this._medicineService.addMedicine(session, body);
        return ResponseEntity.status(HttpStatus.CREATED).body(medicine);
    }

    @AuthRequired
    @GetMapping("/{id}")
    public ResponseEntity<Medicine> get(@PathVariable Long id) {
        try {
            Medicine medicine = this._medicineService.getMedicine(id);
            return ResponseEntity.ok(medicine);
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @AuthRequired
    @PutMapping("/{id}/update")
    public ResponseEntity<Medicine> update(
            @PathVariable Long id,
            @Valid @RequestBody AddMedicineRequest body
    ) {
        try {
            Medicine medicine = this._medicineService.updateMedicine(id, body);
            return ResponseEntity.ok(medicine);
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @AuthRequired
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            this._medicineService.deleteMedicine(id);
            return ResponseEntity.noContent().build();
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Paginated(defaultSize = 20)
    @AuthRequired
    @GetMapping("/list")
    public ResponseEntity<PageResponse<Medicine>> list(Pageable pageable, HttpSession session) {
        return ResponseEntity.ok(PageResponse.from(this._medicineService.listPatientMedicines(session, pageable)));
    }
}
