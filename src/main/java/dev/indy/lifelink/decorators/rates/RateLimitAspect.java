package dev.indy.lifelink.decorators.rates;

import dev.indy.lifelink.exception.RateLimitExceededException;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
public class RateLimitAspect {
    private final String KEY_FORMAT = "%s;%s";

    private final ConcurrentHashMap<String, Bucket> buckets = new ConcurrentHashMap<>();

    private Bucket createBucket(int capacity, int period) {
        return Bucket.builder().addLimit(
            Bandwidth.classic(capacity, Refill.greedy(capacity, Duration.ofSeconds(period)))
        ).build();
    }

    private Bucket resolveBucket(String ip, String path, int capacity, int period) {
        String key = KEY_FORMAT.formatted(ip, path);
        return buckets.computeIfAbsent(key, k -> this.createBucket(capacity, period));
    }

    @Around("@annotation(rateLimited)")
    public Object handleRateLimiting(ProceedingJoinPoint joinPoint, RateLimited rateLimited) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String ip = request.getRemoteAddr();
        String path = request.getRequestURI();

        Bucket bucket = this.resolveBucket(ip, path, rateLimited.value(), rateLimited.seconds());

        if(!bucket.tryConsume(1))
            throw new RateLimitExceededException();

        return joinPoint.proceed();
    }
}
