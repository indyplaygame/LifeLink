package dev.indy.lifelink.controller;

import dev.indy.lifelink.decorators.rates.RateLimited;
import dev.indy.lifelink.model.response.MessageResponse;
import dev.indy.lifelink.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.time.Instant;
import java.util.Map;

@RestController
public class HealthController {
    private final HealthService _healthService;

    @Autowired
    public HealthController(HealthService healthService) {
        this._healthService = healthService;
    }

    @RateLimited(20)
    @GetMapping("/ping")
    public ResponseEntity<MessageResponse> ping() {
        return ResponseEntity.ok(new MessageResponse("Pong!"));
    }

    @RateLimited
    @GetMapping("/health")
    public ResponseEntity<Map<String, ?>> health() {
        return ResponseEntity.ok(Map.of(
            "status", "OK",
            "timestamp", Instant.now().toString(),
            "services", Map.of(
                "database", this._healthService.isDatabaseUp() ? "UP" : "DOWN",
                "email", this._healthService.isEmailServiceUp() ? "UP" : "DOWN"
            )
        ));
    }
}
