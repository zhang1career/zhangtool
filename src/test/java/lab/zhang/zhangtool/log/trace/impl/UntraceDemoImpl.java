package lab.zhang.zhangtool.log.trace.impl;

import lab.zhang.zhangtool.log.trace.ITraceDemo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UntraceDemoImpl implements ITraceDemo {
    @Override
    public void do_something(String traceId) {
        log.info("do something");
        log.info("do something different");
    }
}
