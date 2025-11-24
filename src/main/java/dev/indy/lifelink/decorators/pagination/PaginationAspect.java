package dev.indy.lifelink.decorators.pagination;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PaginationAspect {

    @Around("@annotation(paginated)")
    public Object handlePagination(ProceedingJoinPoint joinPoint, Paginated paginated) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Object[] newArgs = args.clone();

        for(int i = 0; i < args.length; i++) {
            if(!(args[i] instanceof Pageable)) continue;

            Pageable pageable = (Pageable) args[i];
            int page = pageable.getPageNumber();
            int size = pageable.getPageSize();

            if(size <= 0) size = paginated.defaultSize();
            if(size > paginated.maxSize()) size = paginated.maxSize();

            newArgs[i] = PageRequest.of(page, size, pageable.getSort());
            break;
        }

        return joinPoint.proceed(newArgs);
    }
}
