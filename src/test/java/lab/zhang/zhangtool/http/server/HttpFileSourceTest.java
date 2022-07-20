package lab.zhang.zhangtool.http.server;

import lab.zhang.zhangtool.ApplicationTest;
import lab.zhang.zhangtool.http.model.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ApplicationTest.class}, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@PropertySource(value = "classpath:application.properties")
public class HttpFileSourceTest {

    private HttpFileSource target;

    @Before
    public void setUp() throws URISyntaxException {
        URL resource = getClass().getClassLoader().getResource("http/file_source.txt");
        assert resource != null;
        Path absolutePath = Paths.get(resource.toURI()).toAbsolutePath();
        target = new HttpFileSource(absolutePath.toString());
    }

    @Test
    public void test_serve() throws Exception {
        Response response = target.serve();
        assertNotNull(response);
        assertEquals("cafebabe", response.getData());
    }
}