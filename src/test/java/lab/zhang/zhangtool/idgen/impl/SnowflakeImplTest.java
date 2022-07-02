package lab.zhang.zhangtool.idgen.impl;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;

public class SnowflakeImplTest {
    private SnowflakeImpl target;
    private Long id;

    @Before
    public void setUp() {
        target = new SnowflakeImpl(0, 0, 0);
    }

    @Test
    public void test_genId() throws Exception {
        id = target.genId();
        assertNotNull(id);
        System.out.println(id);

        id = target.genId();
        assertNotNull(id);
        System.out.println(id);

        TimeUnit.SECONDS.sleep(1);

        id = target.genId();
        assertNotNull(id);
        System.out.println(id);

        id = target.genId();
        assertNotNull(id);
        System.out.println(id);
    }
}