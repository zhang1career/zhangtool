package lab.zhang.zhangtool.stream.source;

import java.util.stream.Stream;

/**
 * @author zhangrj
 */
public interface StreamSource {
    /**
     * Get source as stream
     * @return byte stream
     * @throws Exception
     */
    Stream<Byte> getStream() throws Exception;
}
