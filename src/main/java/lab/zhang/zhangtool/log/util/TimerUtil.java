package lab.zhang.zhangtool.log.util;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
 * @author zhangrj
 */
@Slf4j
public class TimerUtil {

    static private final String TIMER_START = "start";

    static private final String TIMER_COST = "cost";


    static public void create() {
        MDC.put(TIMER_START, getCurrentTimeMillisStr());
        MDC.put(TIMER_COST, String.valueOf(0));
    }

    static public void tick() {
        String startStr = MDC.get(TIMER_START);
        if (StrUtil.isEmpty(startStr)) {
            startStr = String.valueOf(0);
        }

        long start = Long.parseLong(startStr);
        long now = getCurrentTimeMillis();
        long cost = now - start;
        MDC.put(TIMER_COST, String.valueOf(cost));
    }

    static public void destroy() {
        MDC.remove(TIMER_START);
        MDC.put(TIMER_COST, String.valueOf(0));
    }

    static public String getCostStr() {
        return MDC.get(TIMER_COST);
    }

    static private String getCurrentTimeMillisStr() {
        return String.valueOf(getCurrentTimeMillis());
    }

    static private long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }
}
