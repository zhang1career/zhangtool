package lab.zhang.zhangtool.adapter.mq;

import cn.hutool.core.lang.Pair;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangrj
 */
public class ConsumerAnnotationProcessor implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

    @Autowired
    private ConfigurableEnvironment environment;

    private ApplicationContext applicationContext;

    private Map<String, Pair<Object, ConsumerAnnotationConfig>> topicBeanConfigMap = new ConcurrentHashMap<>();

    private transient volatile boolean triggered;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (triggered) {
            return;
        }
        triggered = true;

        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(Consumer.class);
        for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
            Object bean = entry.getValue();
            Consumer consumerAnnotation = AnnotationUtils.findAnnotation(bean.getClass(), Consumer.class);

            String topic = Objects.requireNonNull(AnnotationUtils.getValue(consumerAnnotation, ConsumerAnnotationConst.VAR_NAME_TOPIC)).toString();
            topic = environment.resolvePlaceholders(topic);

            ConsumerAnnotationConfig config = new ConsumerAnnotationConfig(topic);
            Pair<Object, ConsumerAnnotationConfig> beanConfigPair = Pair.of(bean, config);
            topicBeanConfigMap.put(topic, beanConfigPair);
        }
    }

    private boolean isTriggered() {
        return triggered;
    }
}
