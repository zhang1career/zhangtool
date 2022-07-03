package lab.zhang.zhangtool.log.trace;

public interface ITraceDemo {
    /**
     *
     * @param traceId specified traceId for tracing log, used with @Trace.
     *               Empty string indicates random generation.
     */
    void do_something(String traceId);
}
