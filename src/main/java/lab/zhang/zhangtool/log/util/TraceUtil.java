package lab.zhang.zhangtool.log.util;

import cn.hutool.core.util.StrUtil;
import lab.zhang.zhangtool.basic.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

/**
 * @author zhangrj
 */
@Slf4j
public class TraceUtil {

    static private final String TRACE_ID = "traceId";


    static public void create() {
        String traceId = UuidUtil.uuid();
        if (log.isDebugEnabled()) {
            log.debug("create traceId: {}", traceId);
        }
        MDC.put(TRACE_ID, traceId);
    }

    static public void create(String specifiedTraceId) {
        if (StrUtil.isEmpty(specifiedTraceId)) {
            create();
            return;
        }
        MDC.put(TRACE_ID, specifiedTraceId);
    }

    static public void destroy() {
        MDC.remove(TRACE_ID);
    }
}
