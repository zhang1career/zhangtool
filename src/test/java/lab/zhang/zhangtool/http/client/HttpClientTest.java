package lab.zhang.zhangtool.http.client;

import lab.zhang.zhangtool.ApplicationTest;
import lab.zhang.zhangtool.http.model.Request;
import lab.zhang.zhangtool.http.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationTest.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@PropertySource(value = "classpath:application.properties")
@Slf4j
public class HttpClientTest {

    @Resource
    private HttpClient httpClient;

    private String protocol;
    private String host;
    private int port;
    private String addr;
    private Map<String, String> headMap;
    private Map<String, Object> paramMap;

    @Before
    public void setUp() {
        protocol = "http";
        host = "47.93.157.39";
        port = 20001;
        headMap = new HashMap<>();
        paramMap = new HashMap<>();
    }

    @Test
    public void test_post() throws Exception {
        addr = "";
        Response responseDTO = httpClient.post(
                Request.of(protocol, host, port),
                addr,
                headMap,
                paramMap,
                null,
                Response.class);
        System.out.println(responseDTO);
    }
}