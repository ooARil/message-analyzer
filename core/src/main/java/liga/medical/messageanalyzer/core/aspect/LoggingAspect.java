package liga.medical.messageanalyzer.core.aspect;

import liga.medical.messageanalyzer.core.utils.api.LogHelper;
import liga.medical.model.dto.enums.ServiceType;
import lombok.extern.slf4j.Slf4j;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Autowired
    private LogHelper logHelper;

    @Pointcut("@annotation(liga.medical.messageanalyzer.core.annotation.DbLog)")
    public void dbLogPointcut() {
    }

    @Before("dbLogPointcut()")
    public void beforeAllRestMethodAdvice(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        log.info("UUID {} | Сервис {} | Вызван метод {} : {}() с аргументами {}",
                logHelper.getId(), ServiceType.ANALYZER, className, methodName, Arrays.deepToString(methodArgs));
    }

    @AfterReturning("dbLogPointcut()")
    public void afterReturningAllRestMethodAdvice(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        log.info("UUID {} | Сервис {} | Отработан метод {} : {}() с аргументами {}",
                logHelper.getId(), ServiceType.ANALYZER, className, methodName, Arrays.deepToString(methodArgs));
    }

    @AfterThrowing(pointcut = "dbLogPointcut()", throwing = "exception")
    public void afterThrowingAllRepositoryMethodAdvice(JoinPoint joinPoint,
                                                       Throwable exception) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] methodArgs = joinPoint.getArgs();

        log.info("UUID {} | Сервис {} | Метод завершен с исключением {} : {}() с аргументами {} : Исключение {}",
                logHelper.getId(), ServiceType.ANALYZER, className, methodName, Arrays.deepToString(methodArgs), exception.getMessage());
    }
}
