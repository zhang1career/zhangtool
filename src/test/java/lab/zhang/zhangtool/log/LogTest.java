package lab.zhang.zhangtool.log;

import com.google.code.tempusfugit.concurrency.ConcurrentRule;
import com.google.code.tempusfugit.concurrency.RepeatingRule;
import com.google.code.tempusfugit.concurrency.annotations.Concurrent;
import com.google.code.tempusfugit.concurrency.annotations.Repeating;
import lab.zhang.zhangtool.ApplicationTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationTest.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@PropertySource(value = "classpath:application.properties")
@Slf4j
public class LogTest {

    @Resource
    ILogDemo demo1;

    @Resource
    ILogDemo demo2;

    @Rule
    public ConcurrentRule concurrentRule = new ConcurrentRule();
    @Rule
    public RepeatingRule repeatingRule = new RepeatingRule();


    @Test
    public void test_annotation() throws Exception {
        demo1.do_something("abcd");
        demo2.do_something("1234");
    }


    @Test
    @Concurrent(count = 10)
    @Repeating(repetition = 5)
    public void test_run_thread10_time10() throws Exception {
        demo1.do_something("");
        demo2.do_something("");
    }
}