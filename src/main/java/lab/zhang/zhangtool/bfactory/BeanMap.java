package lab.zhang.zhangtool.bfactory;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BeanMap<T extends IdenticalBean> {
    @Resource
    private List<T> beanList;

    private final Map<String, T> beanMap = new ConcurrentHashMap<>();

    public T get(String id) {
        if (StrUtil.isEmpty(id)) {
            throw new IllegalArgumentException("bean id is empty");
        }
        if (beanMap.isEmpty()) {
            init();
        }
        return beanMap.get(id);
    }

    private synchronized void init() {
        for (T bean : beanList) {
            beanMap.put(bean.getId(), bean);
        }
    }
}
