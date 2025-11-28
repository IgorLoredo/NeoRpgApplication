package com.neo.game.shared.logging;

// import org.aspectj.lang.JoinPoint;
// import org.aspectj.lang.annotation.AfterReturning;
// import org.aspectj.lang.annotation.AfterThrowing;
// import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Component;

// import java.util.Arrays;

// @Aspect
// @Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // @Before("execution(* com.neo.game.character.application.service.*.*(..))")
    public void logBefore(/*JoinPoint joinPoint*/) {
        // logger.info("Method {} called with args: {}", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }

    // @AfterReturning(pointcut = "execution(* com.neo.game.character.application.service.*.*(..))", returning = "result")
    public void logAfterReturning(/*JoinPoint joinPoint, Object result*/) {
        // logger.info("Method {} returned: {}", joinPoint.getSignature().getName(), result);
    }

    // @AfterThrowing(pointcut = "execution(* com.neo.game.character.application.service.*.*(..))", throwing = "ex")
    public void logAfterThrowing(/*JoinPoint joinPoint, Exception ex*/) {
        // logger.error("Method {} threw exception: {}", joinPoint.getSignature().getName(), ex.getMessage());
    }
}
