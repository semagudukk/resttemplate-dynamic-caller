package com.example.callapi.demo.service;


import com.example.callapi.demo.exception.CallApiException;
import com.example.callapi.demo.model.HttpRequestData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CallApiService<T> {

    private final RestTemplate restTemplate;

    /**
     * Dışarıdaki herhangi bir API'yi dinamik olarak çağırır.
     *
     * @param url          Çağrılacak tam URL (dinamik)
     * @param method       HTTP metodu (GET, POST, vb.)
     * @param headersMap   Header'lar (null olabilir)
     * @param body         Request gövdesi (null olabilir)
     * @param responseType Dönen cevabın tipi
     * @return ResponseEntity.getBody() içeriği
     */
    public T callApi(String url,
                     HttpMethod method,
                     Map<String, String> headersMap,
                     Object body,
                     Class<T> responseType) {

        validate(url);

        HttpHeaders headers = new HttpHeaders();
        if (headersMap != null) {
            headersMap.forEach(headers::add);
        }

        HttpEntity<Object> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<T> response = restTemplate.exchange(
                    url,
                    method,
                    entity,
                    responseType
            );
            return response.getBody();
        } catch (RestClientException e) {
            throw new CallApiException("API call failed: " + e.getMessage(), e);
        }
    }

    /**
     * URL'in geçerli olduğundan ve kendi servisimize recursive çağrı yapılmadığından emin olur.
     */
    private void validate(String url) {
        Assert.hasText(url, "URL cannot be null or empty");
        // Kendi uygulamanıza recursive çağrıyı önlemek için basit kontrol
        if (url.contains("localhost") || url.contains("127.0.0.1")) {
            throw new IllegalArgumentException("Recursive call to self (localhost) is not allowed!");
        }
    }

    public Object callApi(HttpRequestData requestData) {
        return requestData;
    }
}