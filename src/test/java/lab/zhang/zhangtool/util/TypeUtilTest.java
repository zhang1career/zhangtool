package lab.zhang.zhangtool.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.util.List;


public class TypeUtilTest {


    @Test
    public void test_getGenToken() throws Exception {
        List<String> result_1 = doTest(String.class);
        System.out.println(result_1);

        List<Integer> result_2 = doTest(Integer.class);
        System.out.println(result_2);

        List<Long> result_3 = doTest(Long.class);
        System.out.println(result_3);
    }

    static public <T> List<T> doTest(Class<T> clazz) throws Exception {
        TypeToken<?> typeOfT = TypeUtil.getGenToken(List.class, clazz);
        List<T> list = new Gson().fromJson("[1, 2, 3]", typeOfT.getType());

        for (Object o : list) {
            System.out.println("value : " + o + ", type : " + o.getClass());
        }

        return list;
    }
}