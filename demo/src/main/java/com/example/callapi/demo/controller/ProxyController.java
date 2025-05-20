package com.example.callapi.demo.controller;

import com.example.callapi.demo.service.CallApiService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/proxy")
@RequiredArgsConstructor
public class ProxyController {

    private final CallApiService<String> callApiService;

    @GetMapping
    public String doGet(@RequestParam("url") String url,
                        @RequestHeader Map<String, String> headers) {
        return callApiService.callApi(
                url,
                HttpMethod.GET,
                headers.isEmpty() ? null : headers,
                null,
                String.class
        );sfsffssssfsfsfsf
    }

    @PostMapping
    public String doPost(@RequestBody ProxyRequest req) {
        return callApiService.callApi(
                req.getUrl(),
                HttpMethod.POST,
                req.getHeaders(),
                req.getBody(),
                String.class
        );
    }

    @Data
    static class ProxyRequest {
        private String url;
        private Map<String, String> headers;
        private Object body;
    }
}