package lab.zhang.zhangtool.http.server;

import lab.zhang.zhangtool.http.model.Response;
import lab.zhang.zhangtool.stream.source.StreamSource;
import lab.zhang.zhangtool.stream.source.impl.FileSourceImpl;
import lab.zhang.zhangtool.util.ByteUtil;

/**
 * @author zhangrj
 */
public class HttpFileSource {

    private final StreamSource streamSource;

    public HttpFileSource(String filePath) {
        this.streamSource = new FileSourceImpl(filePath);
    }

    public Response serve() throws Exception {
        Byte[] byteObjects = streamSource.getStream().toArray(Byte[]::new);
        String data = ByteUtil.drain(byteObjects);

        Response response = new Response();
        response.setCode(0);
        response.setMsg("ok");
        response.setData(data);

        return response;
    }
}
