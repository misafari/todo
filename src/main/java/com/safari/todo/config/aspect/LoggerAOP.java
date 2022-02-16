package com.safari.todo.config.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggerAOP {
    Logger logger = LoggerFactory.getLogger(LoggerAOP.class);

    @Before("execution(* com.safari.todo.service.*.*(..))")
    public void logMethodExecution(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        String params = Arrays.toString(joinPoint.getArgs());
        logger.debug("Method [" + method + "] gets called with parameters " + params);
    }

    @Around("@annotation(com.safari.todo.config.aspect.MethodDurationLog)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long duration = System.currentTimeMillis() - startTime;
        logger.debug("Execution took [" + duration + "ms]");
        return proceed;
    }

}
