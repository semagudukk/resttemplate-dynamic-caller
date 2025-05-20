package com.example.callapi.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;

import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpRequestData {

    private String url;
    private HttpMethod method;
    private Object body;
    private Map<String, String> headers;
    private String responseType;

}