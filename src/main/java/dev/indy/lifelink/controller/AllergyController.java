package dev.indy.lifelink.controller;

import dev.indy.lifelink.auth.AuthRequired;
import dev.indy.lifelink.decorators.pagination.Paginated;
import dev.indy.lifelink.exception.EntityNotFoundException;
import dev.indy.lifelink.exception.HttpException;
import dev.indy.lifelink.model.Allergy;
import dev.indy.lifelink.model.request.AddAllergyRequest;
import dev.indy.lifelink.model.response.PageResponse;
import dev.indy.lifelink.service.AllergyService;
import dev.indy.lifelink.validation.ValidationGroups;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/allergies")
public class AllergyController {
    private final AllergyService _allergyService;

    @Autowired
    public AllergyController(AllergyService allergyService) {
        this._allergyService = allergyService;
    }

    @AuthRequired
    @PostMapping(value = "/add")
    public ResponseEntity<Allergy> add(
        @Validated(ValidationGroups.OnCreate.class) @RequestBody AddAllergyRequest body, HttpSession session
    ) {
        Allergy allergy = this._allergyService.addAllergy(session, body);
        return ResponseEntity.status(HttpStatus.CREATED).body(allergy);
    }

    @AuthRequired
    @GetMapping("/{id}")
    public ResponseEntity<Allergy> get(@PathVariable Long id, HttpSession session) {
        try {
            Allergy allergy = this._allergyService.getAllergy(session, id);
            return ResponseEntity.ok(allergy);
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @AuthRequired
    @PutMapping("/{id}/update")
    public ResponseEntity<Allergy> update(
        @PathVariable Long id,
        @Validated(ValidationGroups.OnUpdate.class) @RequestBody AddAllergyRequest body,
        HttpSession session
    ) {
        try {
            Allergy allergy = this._allergyService.updateAllergy(session, id, body);
            return ResponseEntity.ok(allergy);
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @AuthRequired
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(HttpSession session, @PathVariable Long id) {
        try {
            this._allergyService.deleteAllergy(session, id);
            return ResponseEntity.noContent().build();
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Paginated(defaultSize = 20)
    @AuthRequired
    @GetMapping("/list")
    public ResponseEntity<PageResponse<Allergy>> list(Pageable pageable, HttpSession session) {
        return ResponseEntity.ok(PageResponse.from(this._allergyService.listPatientAllergies(session, pageable)));
    }
}
