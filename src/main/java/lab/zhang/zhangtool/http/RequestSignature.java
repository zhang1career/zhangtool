package lab.zhang.zhangtool.http;

import lombok.Data;

/**
 * @author zhangrj
 */
@Data
public class RequestSignature {
    static public RequestSignature of(String protocol, String host, int port) {
        return new RequestSignature(protocol, host, port);
    }

    private String protocol;
    private String host;
    private int port;

    private RequestSignature(String protocol, String host, int port) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
    }
}
