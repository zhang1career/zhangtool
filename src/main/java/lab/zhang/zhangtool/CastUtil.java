package lab.zhang.zhangtool;

/**
 * @author zhangrj
 */
public class CastUtil {

    @SuppressWarnings("unchecked")
    static public <T> T from(Object obj) {
        return (T) obj;
    }

    private CastUtil() {
        throw new AssertionError();
    }
}
