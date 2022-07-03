package lab.zhang.zhangtool.log.impl;

import lab.zhang.zhangtool.log.ILogDemo;
import lab.zhang.zhangtool.log.metric.MetricTime;
import lab.zhang.zhangtool.log.trace.Trace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LogDemoImpl implements ILogDemo {
    @Override
    @Trace
    @MetricTime("do_something")
    public void do_something(String traceId) throws InterruptedException {
        log.info("demo start...");
        Thread.sleep(1000);
        log.info("demo stop.");
    }
}
