package dev.indy.lifelink.controller;

import dev.indy.lifelink.auth.AuthRequired;
import dev.indy.lifelink.decorators.pagination.Paginated;
import dev.indy.lifelink.exception.EntityNotFoundException;
import dev.indy.lifelink.exception.HttpException;
import dev.indy.lifelink.model.ChronicDisease;
import dev.indy.lifelink.model.request.AddChronicDiseaseRequest;
import dev.indy.lifelink.model.response.PageResponse;
import dev.indy.lifelink.service.ChronicDiseaseService;
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
@RequestMapping("/diseases")
public class ChronicDiseaseController {
    private final ChronicDiseaseService _chronicDiseaseService;

    @Autowired
    public ChronicDiseaseController(ChronicDiseaseService chronicDiseaseService) {
        this._chronicDiseaseService = chronicDiseaseService;
    }

    @AuthRequired
    @PostMapping(value = "/add")
    public ResponseEntity<ChronicDisease> add(
        @Validated(ValidationGroups.OnCreate.class) @RequestBody AddChronicDiseaseRequest body, HttpSession session
    ) {
        ChronicDisease disease = this._chronicDiseaseService.addChronicDisease(session, body);
        return ResponseEntity.status(HttpStatus.CREATED).body(disease);
    }

    @AuthRequired
    @GetMapping("/{id}")
    public ResponseEntity<ChronicDisease> get(@PathVariable Long id) {
        try {
            ChronicDisease disease = this._chronicDiseaseService.getChronicDisease(id);
            return ResponseEntity.ok(disease);
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @AuthRequired
    @PutMapping("/{id}/update")
    public ResponseEntity<ChronicDisease> update(
        @PathVariable Long id,
        @Validated(ValidationGroups.OnUpdate.class) @RequestBody AddChronicDiseaseRequest body
    ) {
        try {
            ChronicDisease disease = this._chronicDiseaseService.updateChronicDisease(id, body);
            return ResponseEntity.ok(disease);
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @AuthRequired
    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            this._chronicDiseaseService.deleteChronicDisease(id);
            return ResponseEntity.noContent().build();
        } catch(EntityNotFoundException e) {
            throw new HttpException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @Paginated(defaultSize = 20)
    @AuthRequired
    @GetMapping("/list")
    public ResponseEntity<PageResponse<ChronicDisease>> list(Pageable pageable, HttpSession session) {
        return ResponseEntity.ok(PageResponse.from(this._chronicDiseaseService.listPatientChronicDiseases(session, pageable)));
    }
}
