package lab.zhang.zhangtool.log;

public interface ILogDemo {
    /**
     *
     * @param traceId specified traceId for tracing log, used with @Trace.
     *               Empty string indicates random generation.
     */
    void do_something(String traceId) throws Exception;
}
