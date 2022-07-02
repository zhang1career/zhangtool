package lab.zhang.zhangtool.basic;

import java.util.UUID;

/**
 * @author zhangrj
 */
public class UuidUtil {
    static public String uuid() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
