package lab.zhang.zhangtool.log.trace;

import lab.zhang.zhangtool.ApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import org.junit.*;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationTest.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@PropertySource(value = "classpath:application.properties")
@Slf4j
public class TraceUtilTest {

    @Resource(name = "traceDemoImpl")
    ITraceDemo tracer;

    @Resource(name = "untraceDemoImpl")
    ITraceDemo unTracer;

    @Test
    public void test_log_with_trace_id() throws Exception {
        TraceUtil.create();
        log.info("hello");
        log.warn("world");
        TraceUtil.destroy();

        TraceUtil.create();
        log.info("foo");
        TraceUtil.destroy();
        log.error("bar");

        TraceUtil.create("abcd");
        log.info("abcd");
        TraceUtil.destroy();

    }

    @Test
    public void test_annotation() throws Exception {
        tracer.do_something("");
        unTracer.do_something("5678");
    }
}