package lab.zhang.zhangtool;

/**
 * @author zhangrj
 */
public class ArrayUtil {
    static public boolean isEmpty(Object[] arr) {
        return arr == null || arr.length <= 0;
    }

    static public Object[] parseArray(Object obj) {
        if (obj.getClass().isArray()) {
            return (Object[]) obj;
        }
        throw new RuntimeException("The param type is wrong");
    }

    private ArrayUtil() {
        throw new AssertionError();
    }
}
