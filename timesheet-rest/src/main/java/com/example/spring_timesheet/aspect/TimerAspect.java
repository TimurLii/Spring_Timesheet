package com.example.spring_timesheet.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
@Slf4j
@Aspect
@Component
public class TimerAspect {

    @Pointcut("@annotation(com.example.spring_timesheet.aspect.Timer)")
    public void timerPointcut(){}

    @Around(value = "timerPointcut()")
    public Object    aroundTimesheetServiceMethods(@NotNull ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        try{
            return proceedingJoinPoint.proceed();
        }finally {
            long duration = System.currentTimeMillis() - start;
            log.info("TimesheetService#{} duration = {}ms",  proceedingJoinPoint.getSignature().getName(),duration);
        }
    }
}
