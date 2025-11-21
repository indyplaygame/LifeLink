package dev.indy.lifelink.controller;

import dev.indy.lifelink.model.response.MessageResponse;
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
    private final DataSource _dataSource;

    @Autowired
    public HealthController(DataSource dataSource) {
        this._dataSource = dataSource;
    }

    private boolean isDatabaseUp() {
        try(Connection ignored = this._dataSource.getConnection()) {
            return true;
        } catch(Exception ex) {
            return false;
        }
    }

    @GetMapping("/ping")
    public ResponseEntity<MessageResponse> ping() {
        return ResponseEntity.ok(new MessageResponse("Pong!"));
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, ?>> health() {
        return ResponseEntity.ok(Map.of(
            "status", "OK",
            "timestamp", Instant.now().toString(),
            "services", Map.of(
                "database", this.isDatabaseUp() ? "UP" : "DOWN"
            )
        ));
    }
}
