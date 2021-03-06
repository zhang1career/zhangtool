package lab.zhang.zhangtool.log.trace;

import org.springframework.core.annotation.Order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * Notice: User defined priority takes 100 as the highest value.
 *
 * @author zhangrj
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Order(100)
public @interface Trace {
}
