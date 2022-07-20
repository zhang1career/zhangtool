package lab.zhang.zhangtool.util;

import org.apache.commons.lang3.ArrayUtils;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

/**
 * @author zhangrj
 */
public class ByteUtil {
    static public Stream<Byte> stream(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        return Stream.generate(byteBuffer::get)
                .limit(byteBuffer.capacity());
    }

    static public String drain(Byte[] byteObjects) {
        byte[] bytes = ArrayUtils.toPrimitive(byteObjects);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    private ByteUtil() {
        throw new AssertionError();
    }
}
