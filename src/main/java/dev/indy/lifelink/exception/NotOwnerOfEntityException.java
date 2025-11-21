package dev.indy.lifelink.exception;

import org.springframework.http.HttpStatus;

public class NotOwnerOfEntityException extends HttpException {
    public NotOwnerOfEntityException(Class<?> entityClass) {
        super(HttpStatus.FORBIDDEN, "This %s does not belong to the authenticated patient.".formatted(entityClass.getSimpleName()));
    }
}
