package lab.zhang.zhangtool;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;


/**
 * @author zhangrj
 */
public class MapUtil {

    static public <T> T mapToObject(Map<String, ?> map, Class<T> clazz) throws Exception {
        if (map == null) {
            return null;
        }

        T obj = clazz.newInstance();
        BeanUtils.populate(obj, map);
        return obj;
    }

    static public Map<?, ?> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        return new BeanMap(obj);
    }

    private MapUtil() {
        throw new AssertionError();
    }
}
