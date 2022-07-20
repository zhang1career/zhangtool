package lab.zhang.zhangtool.util;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ListUtil {

    @NotNull
    static public <P, R> List<R> columnOf(@NotNull List<P> inputList, Convertible<P, R> convertor) {
        List<R> output = new ArrayList<>(inputList.size());
        for (P input : inputList) {
            output.add(convertor.covertFrom(input));
        }

        return output;
    }


    @NotNull
    static public <K, P, R> Map<K, R> columnOf(@NotNull Map<K, P> inputMap, Convertible<P, R> convertor) {
        Map<K, R> output = new HashMap<>(inputMap.size());
        for (Map.Entry<K, P> entry : inputMap.entrySet()) {
            output.put(entry.getKey(), convertor.covertFrom(entry.getValue()));
        }

        return output;
    }

    /**
     * Convert Iterable to LinkedHashMap, indexed by a dedicated field.
     *
     * Features:
     *   The output order of LinkedHashMap is as same as the input order.
     *
     * @param iter
     * @param convertor
     * @param <K>
     * @param <V>
     * @return LinkedHashMap
     */
    @NotNull
    static public <K, V> Map<K, V> indexById(@NotNull Iterable<V> iter, Convertible<V, K> convertor) {
        if (convertor == null) {
            throw new IllegalArgumentException("The convertor should not be null.");
        }

        Map<K, V> map = new LinkedHashMap<>();
        for (V item : iter) {
            map.put(convertor.covertFrom(item), item);
        }
        return map;
    }

    private ListUtil() {
        throw new AssertionError();
    }


    public interface Convertible<P, R> {
        R covertFrom(P param);
    }
}
