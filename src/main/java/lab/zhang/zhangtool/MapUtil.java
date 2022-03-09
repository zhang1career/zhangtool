package lab.zhang.zhangtool;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;

import java.util.HashMap;
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


    static public <K, V> Map<K, V> intersect(Map<K, V> map, K[] whiteKeys) {
        Map<K, V> ret = new HashMap<>(0);

        if (ArrayUtil.isEmpty(whiteKeys)) {
            return ret;
        }

        for (K whiteKey : whiteKeys) {
            if (!map.containsKey(whiteKey)) {
                continue;
            }
            ret.put(whiteKey, map.get(whiteKey));
        }

        return ret;
    }

    private MapUtil() {
        throw new AssertionError();
    }
}
