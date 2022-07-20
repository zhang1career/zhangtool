package lab.zhang.zhangtool.http.model;

import lombok.Data;

/**
 * Http request model
 *
 * @author zhangrj
 */
@Data
public class Request {
    static public Request of(String protocol, String host, int port) {
        return new Request(protocol, host, port);
    }

    private String protocol;
    private String host;
    private int port;

    private Request(String protocol, String host, int port) {
        this.protocol = protocol;
        this.host = host;
        this.port = port;
    }
}
