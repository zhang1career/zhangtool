package lab.zhang.zhangtool;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseRepo {

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


    @NotNull
    static public <K, V> Map<K, V> indexById(@NotNull List<V> list, Convertible<V, K> convertor) {
        Map<K, V> map = new HashMap<>(list.size());
        for (V item : list) {
            map.put(convertor.covertFrom(item), item);
        }
        return map;
    }


    public interface Convertible<P, R> {
        R covertFrom(P param);
    }
}
