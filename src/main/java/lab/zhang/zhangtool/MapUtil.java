package lab.zhang.zhangtool;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;


/**
 * @author zhangrj
 */
public class MapUtil {
    static private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static public <T> T mapToObject(Map<String, ?> map, Class<T> clazz) throws Exception {
        if (map == null) {
            return null;
        }
        return OBJECT_MAPPER.convertValue(map, clazz);
    }


    static public Map<?, ?> objectToMap(Object obj) {
        if (obj == null) {
            return null;
        }
        return OBJECT_MAPPER.convertValue(obj, Map.class);
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
