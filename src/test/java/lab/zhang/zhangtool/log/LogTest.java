package lab.zhang.zhangtool.log;

import lab.zhang.zhangtool.ApplicationTest;
import lombok.extern.slf4j.Slf4j;
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

    @Test
    public void test_annotation() throws Exception {
        demo1.do_something("abcd");
        demo2.do_something("1234");
    }
}