package dev.indy.lifelink.controller;

import dev.indy.lifelink.auth.AuthRequired;
import dev.indy.lifelink.decorators.pagination.Paginated;
import dev.indy.lifelink.exception.EntityHasRelatedDataException;
import dev.indy.lifelink.exception.EntityNotFoundException;
import dev.indy.lifelink.exception.HttpException;
import dev.indy.lifelink.model.Medicine;
import dev.indy.lifelink.model.Vaccine;
import dev.indy.lifelink.model.request.AddMedicineRequest;
import dev.indy.lifelink.model.request.AddVaccineRequest;
import dev.indy.lifelink.model.response.PageResponse;
import dev.indy.lifelink.service.MedicineService;
import dev.indy.lifelink.service.VaccineService;
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
@RequestMapping("/vaccines")
public class VaccineController {
    private final VaccineService _vaccineService;

    @Autowired
    public VaccineController(VaccineService vaccineService) {
        this._vaccineService = vaccineService;
    }

    @AuthRequired
    @PostMapping(value = "/add")
    public ResponseEntity<Vaccine> add(
            @Validated(ValidationGroups.OnCreate.class) @RequestBody AddVaccineRequest body, HttpSession session
    ) {
        Vaccine vaccine = this._vaccineService.addVaccine(session, body);
        return ResponseEntity.status(HttpStatus.CREATED).body(vaccine);
    }

    @AuthRequired
    @GetMapping("/{id}")
    public ResponseEntity<Vaccine> get(@PathVariable Long id) {
        try {
            Vaccine vaccine = this._vaccineService.getVaccine(id);
            return ResponseEntity.ok(vaccine);
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @AuthRequired
    @PutMapping("/{id}/update")
    public ResponseEntity<Vaccine> update(
            @PathVariable Long id,
            @Validated(ValidationGroups.OnUpdate.class) @RequestBody AddVaccineRequest body
    ) {
        try {
            Vaccine vaccine = this._vaccineService.updateVaccine(id, body);
            return ResponseEntity.ok(vaccine);
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @AuthRequired
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            this._vaccineService.deleteVaccine(id);
            return ResponseEntity.noContent().build();
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        } catch(EntityHasRelatedDataException e) {
            throw new HttpException(HttpStatus.CONFLICT, e.getMessage());
        }
    }

    @Paginated(defaultSize = 20)
    @AuthRequired
    @GetMapping("/list")
    public ResponseEntity<PageResponse<Vaccine>> list(Pageable pageable, HttpSession session) {
        return ResponseEntity.ok(PageResponse.from(this._vaccineService.listPatientVaccines(session, pageable)));
    }
}
