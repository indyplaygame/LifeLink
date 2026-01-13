package dev.indy.lifelink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;

@Service
public class HealthService {
    private final DataSource _dataSource;
    private final EmailService _emailService;

    @Autowired
    public HealthService(DataSource dataSource, EmailService emailService) {
        this._dataSource = dataSource;
        this._emailService = emailService;
    }

    public boolean isDatabaseUp() {
        try(Connection ignored = this._dataSource.getConnection()) {
            return true;
        } catch(Exception ex) {
            return false;
        }
    }

    public boolean isEmailServiceUp() {
        return this._emailService.isUp();
    }
}
