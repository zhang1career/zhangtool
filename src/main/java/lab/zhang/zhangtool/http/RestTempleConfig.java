package lab.zhang.zhangtool.http;

import org.hobsoft.spring.resttemplatelogger.LoggingCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangrj
 */
@Configuration
public class RestTempleConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .customizers(new LoggingCustomizer())
                .build();
    }
}
