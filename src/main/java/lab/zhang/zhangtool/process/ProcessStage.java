package lab.zhang.zhangtool.process;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 状态节点
 *
 * 根据状态节点之间的依赖关系，约束流程执行过程
 *
 * @author zhangrj
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface State {
    String process() default "";
    int[] dependsOn() default {};
}
