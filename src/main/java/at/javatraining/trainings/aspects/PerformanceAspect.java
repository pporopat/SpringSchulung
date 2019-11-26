package at.javatraining.trainings.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

@Aspect
@Component
@Profile("performance")
@Slf4j
public class PerformanceAspect {
    @Value("${performance.threshold.millis}")
    private long threshold;

    @PostConstruct
    public void init() {
        log.info("Aspect initialized!");
    }

    @Around("execution(* at.javatraining.trainings.services..*.*(..)) ||" +
            "execution(* at.javatraining.trainings.config..*.*(..)))")
    public Object measurePerformance(ProceedingJoinPoint pjp) throws Throwable {
        Instant start = Instant.now();
        Object result = pjp.proceed();
        long durationMillis = ChronoUnit.MILLIS.between(start, Instant.now());

        if(durationMillis>=threshold) {
            String className = pjp.getTarget().getClass().getName();
            String methodName = pjp.getSignature().getName();
            String args = Arrays.toString(pjp.getArgs());

            String message = String.format("Performance Alert duration: %d %s.%s(%s)", durationMillis, className, methodName, args);
            log.warn(message);
        }

        return result;
    }
}
