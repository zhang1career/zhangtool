package lab.zhang.zhangtool.util;

import java.util.UUID;

/**
 * @author zhangrj
 */
public class UuidUtil {
    static public String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    private UuidUtil() {
        throw new AssertionError();
    }
}
