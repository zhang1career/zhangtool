package lab.zhang.zhangtool.util;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;


/**
 * @author zhangrj
 */
public class StrUtil {
    @Contract(pure = true)
    static public String[] explode(@NotNull String str, String separator) {
        return str.split(separator);
    }

    static public String implode(Iterable<?> iterable, String separator) {
        return StringUtils.join(iterable, separator);
    }

    private StrUtil() {
        throw new AssertionError();
    }
}
