package lab.zhang.zhangtool.util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GcLogUtilTest {

    @Test
    public void test_gclog() throws Exception {
        GcLogUtil.startLoggingGc();

        System.out.println("begin");

        List<List<Object>> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                list.add(new ArrayList<>(1000));
            }
        }

        System.out.println("end");
    }
}