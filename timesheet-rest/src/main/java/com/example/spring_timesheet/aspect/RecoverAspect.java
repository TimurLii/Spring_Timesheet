package com.example.spring_timesheet.aspect;
//
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@Aspect
//@Component
//public class RecoverAspect {
//    @Pointcut("@annotation(com.example.spring_timesheet.aspect.Recover)")
//    public void recoverPointcut(){}
//
//    @AfterThrowing(value = "recoverPointcut()",  throwing = "ex")
//    public void aroundCreateObjects(JoinPoint joinPoint, Throwable ex){
//        Map<String, Object> fieldToValue = getNameArgsAndValue(joinPoint);
//        log.info("AfterThrowing -> examinationAspectRecover()#{}. ",fieldToValue);
//    }
//    @NotNull
//    private Map<String, Object> getNameArgsAndValue(JoinPoint joinPoint) {
//        Object[] argumentsMethod = joinPoint.getArgs();
//
//        Map<String, Object> fieldToValue = new HashMap<>();
//
//        for(Object arg: argumentsMethod){
//            fieldToValue.put(arg.getClass().getSimpleName(), arg);
//        }
//        return fieldToValue;
//    }
//}
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
public class RecoverAspect {
    private static final Logger logger = Logger.getLogger(RecoverAspect.class.getName());

    @Around("@annotation(com.example.spring_timesheet.aspect.Recover)")
    public Object handleRecover(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Recover recoverAnnotation = signature.getMethod().getAnnotation(Recover.class);
        System.out.println("asdasdadadasdasdasdadsadsa????????????????????????");

        // Получаем тип возвращаемого значения метода
        Class<?> returnType = signature.getReturnType();

        try {
            // Попытка выполнения метода
            return joinPoint.proceed();
        } catch (Throwable ex) {
            // Проверка, нужно ли исключение игнорировать
            if (Arrays.stream(recoverAnnotation.noRecoverFor()).anyMatch(noRecover -> noRecover.isInstance(ex))) {
                throw new RuntimeException(ex); // Если исключение указано в noRecoverFor, не обрабатываем его
            }

            // Логирование исключения
            logger.severe("Exception caught in method: " + signature.getName() + " - " + ex);

            // Возвращаем default-значение в зависимости от типа
            return getDefaultValue(returnType);
        }
    }

    // Метод для получения значения по умолчанию для указанного типа
    private Object getDefaultValue(Class<?> returnType) {
        if (!returnType.isPrimitive()) {
            return null; // Для ссылочных типов возвращаем null
        }

        if (returnType == boolean.class) return false;
        if (returnType == byte.class) return (byte) 0;
        if (returnType == char.class) return '\u0000';
        if (returnType == short.class) return (short) 0;
        if (returnType == int.class) return 0;
        if (returnType == long.class) return 0L;
        if (returnType == float.class) return 0f;
        if (returnType == double.class) return 0d;

        throw new UnsupportedOperationException("Unsupported primitive type: " + returnType);
    }
}
