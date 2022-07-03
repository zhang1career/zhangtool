package lab.zhang.zhangtool.log.metric.impl;

import lab.zhang.zhangtool.log.metric.IMetricTimeDemo;
import lab.zhang.zhangtool.log.metric.MetricTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MetricTimeDemoImpl implements IMetricTimeDemo {
    @Override
    @MetricTime("aloha")
    public void do_something() throws InterruptedException {
        log.info("do something");
        Thread.sleep(1000);
        log.info("do something different");
    }
}
