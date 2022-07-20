package lab.zhang.zhangtool.http.client;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lab.zhang.zhangtool.http.model.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.net.URI;
import java.util.Collections;
import java.util.Map;

/**
 * @author zhangrj
 */
@Component
@Slf4j
public class HttpClient {

    static private HttpClient httpClient;

    @Resource
    private RestTemplate restTemplate;

    @PostConstruct
    private void init() {
        httpClient = this;
        httpClient.restTemplate = this.restTemplate;
    }

    public <P, R> R post(Request request, String addr, Map<String, String> headMap, Map<String, Object> paramMap, P httpBody, Class<R> resultClazz) {
        String url = buildUrl(request, addr);
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
            responseEntity = httpClient.restTemplate.exchange(uri, httpMethod, httpEntity, String.class);
        } catch (RestClientException e) {
            log.error("HttpService | post | error: " + e.getMessage());
        }
        if (responseEntity == null) {
            return null;
        }
        return responseEntity.getBody();
    }

    private String buildUrl(Request request, String path) {
        String pathPrefix = path.startsWith("/") ? "" : "/";
        return request.getProtocol() + "://" + request.getHost() + ":" + request.getPort() + pathPrefix + path;
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
