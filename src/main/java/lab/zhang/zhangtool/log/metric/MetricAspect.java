package lab.zhang.zhangtool.log.metric;

import lab.zhang.zhangtool.log.util.TimerUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author zhangrj
 */
@Aspect
@Component
@Slf4j
public class MetricAspect {

    @Around("@annotation(metricTime)")
    public Object metricTime(ProceedingJoinPoint joinPoint, MetricTime metricTime) throws Throwable {
        if (metricTime == null) {
            throw new RuntimeException("MetricTime should not be null.");
        }

        TimerUtil.create();
        try {
            return joinPoint.proceed();
        } finally {
            TimerUtil.tick();
            log.info("metric.time | {} costs {}ms", metricTime.value(), TimerUtil.getCostStr());
            TimerUtil.destroy();
        }
    }
}
