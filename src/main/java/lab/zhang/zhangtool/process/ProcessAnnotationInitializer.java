package lab.zhang.zhangtool.process;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author zhangrj
 */
@Configuration
public class ProcessAnnotationProcessor implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private transient volatile boolean triggered;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(@NotNull ContextRefreshedEvent contextRefreshedEvent) {
        if (isTriggered()) {
            return;
        }
        triggered = true;

        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(Process.class);
        for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();

            String[] split = clazz.getName().split("\\$");
            String originClassName = split[0];

            Class<?> originClass = getClass().getClassLoader().loadClass(originClassName);
            Method[] methods = originClass.getDeclaredMethods();
            for (Method method : methods) {
                ProcessStage processStageAnnotation = method.getDeclaredAnnotation(ProcessStage.class);
                System.out.println(processStageAnnotation);
            }
        }
    }

    private boolean isTriggered() {
        return triggered;
    }
}
