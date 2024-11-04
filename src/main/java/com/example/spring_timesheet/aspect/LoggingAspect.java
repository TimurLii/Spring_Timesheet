package com.example.spring_timesheet.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class LoggingAspect {
    @Before(value = "execution(* com.example.spring_timesheet.model.serevice.timesheetSerevice.TimesheetService.*(..))")
    public void beforeTimesheetServiceFindById(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        Map<String, Object> fieldToValue = getNameArgsAndValue(joinPoint);

        log.info("Before ->  TimesheetService#{}.#{} ", name,fieldToValue);
    }

    @After(value = "execution(* com.example.spring_timesheet.model.serevice.timesheetSerevice.TimesheetService.*(..))")
    public void afterTimesheetServiceFindById(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        Map<String, Object> fieldToValue = getNameArgsAndValue(joinPoint);
        log.info("After -> TimesheetService#{}.#{} ", name,fieldToValue);
    }

    @NotNull
    private Map<String, Object> getNameArgsAndValue(JoinPoint joinPoint) {
        Object[] argumentsMethod = joinPoint.getArgs();

        Map<String, Object> fieldToValue = new HashMap<>();

        for(Object arg: argumentsMethod){
            fieldToValue.put(arg.getClass().getSimpleName(), arg);
        }
        return fieldToValue;
    }


}
