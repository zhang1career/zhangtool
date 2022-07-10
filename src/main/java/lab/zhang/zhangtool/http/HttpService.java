package lab.zhang.zhangtool.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.hobsoft.spring.resttemplatelogger.LoggingCustomizer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Collections;
import java.util.Map;

/**
 * @author zhangrj
 */
@Component
@Slf4j
public class HttpService {

    static private HttpService httpService;

    @Resource
    private RestTemplate restTemplate;

    @PostConstruct
    private void init() {
        httpService = this;
        httpService.restTemplate = this.restTemplate;
    }

    public <P, R> R post(RequestSignature signature, String addr, Map<String, String> headMap, Map<String, Object> paramMap, P httpBody, Class<R> resultClazz) {
        String url = buildUrl(signature, addr);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url);
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            uriBuilder.queryParam(entry.getKey(), entry.getValue());
        }

        HttpHeaders httpHeaders = buildHeaders(headMap);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<P> httpEntity = new HttpEntity<>(httpBody, httpHeaders);

        String responseStr = httpRequest(uriBuilder.build().encode().toUri(), HttpMethod.POST, httpEntity);

        Gson gson = new Gson();
        return gson.fromJson(responseStr, TypeToken.get(resultClazz).getType());
    }

    private <P> String httpRequest(URI uri, HttpMethod httpMethod, HttpEntity<P> httpEntity) {
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = httpService.restTemplate.exchange(uri, httpMethod, httpEntity, String.class);
        } catch (RestClientException e) {
            log.error("HttpService | post | error: " + e.getMessage());
        }
        if (responseEntity == null) {
            return null;
        }
        return responseEntity.getBody();
    }

    private String buildUrl(RequestSignature requestSignature, String path) {
        String pathPrefix = path.startsWith("/") ? "" : "/";
        return requestSignature.getProtocol() + "://" + requestSignature.getHost() + ":" + requestSignature.getPort() + pathPrefix + path;
    }

    public HttpHeaders buildHeaders(Map<String, String> headerMap) {
        HttpHeaders httpHeaders = new HttpHeaders();
        if (headerMap == null) {
            return httpHeaders;
        }

        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            httpHeaders.add(entry.getKey(), entry.getValue());
        }
        return httpHeaders;
    }
}
