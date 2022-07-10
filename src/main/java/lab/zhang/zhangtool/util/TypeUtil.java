package lab.zhang.zhangtool.util;

import com.google.gson.reflect.TypeToken;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

/**
 * @author zhangrj
 */
public class TypeUtil {

    static TypeToken<?> getGenToken(final Class<?> raw, final Class<?> gen) throws Exception {
        Constructor<ParameterizedTypeImpl> constr = ParameterizedTypeImpl.class.getDeclaredConstructor(Class.class, Type[].class, Type.class);
        constr.setAccessible(true);
        ParameterizedTypeImpl paramType = constr.newInstance(raw, new Type[]{gen}, null);

        return TypeToken.get(paramType);
    }
}
