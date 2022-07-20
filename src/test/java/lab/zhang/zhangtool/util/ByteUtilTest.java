package lab.zhang.zhangtool.util;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ByteUtilTest {

    private byte[] bytes;

    @Before
    public void setUp() {
        bytes = new byte[]{0xC, 0xA, 0xF, 0xE, 0xB, 0xA, 0xB, 0xE};
    }

    @Test
    public void test_stream() throws Exception {
        String result = ByteUtil.stream(bytes)
                .map(Integer::toHexString)
                .collect(Collectors.joining());
        assertEquals("cafebabe", result);
    }
}