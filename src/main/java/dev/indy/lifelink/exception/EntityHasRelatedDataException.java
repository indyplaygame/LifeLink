package dev.indy.lifelink.exception;

public class EntityHasRelatedDataException extends Exception {
    public EntityHasRelatedDataException(Class<?> entity, Class<?> related) {
        super("Entity of type %s has related data of type %s.".formatted(entity.getSimpleName(), related.getSimpleName()));
    }
}
