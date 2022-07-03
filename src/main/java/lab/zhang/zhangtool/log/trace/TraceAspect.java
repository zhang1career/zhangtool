package lab.zhang.zhangtool.log.trace;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author zhangrj
 */
@Aspect
@Component
public class TraceAspect {

    @Pointcut("execution(@lab.zhang.zhangtool.log.trace.Trace * *(String, ..)) && args(traceId)")
    public void trace(String traceId) {
    }

    @Around(value = "trace(traceId)", argNames = "joinPoint,traceId")
    public Object trace(ProceedingJoinPoint joinPoint, String traceId) throws Throwable {
        TraceUtil.create(traceId);
        try {
            return joinPoint.proceed();
        } finally {
            TraceUtil.destroy();
        }
    }
}
