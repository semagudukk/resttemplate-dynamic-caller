package com.example.callapi.demo.controller;

import com.example.callapi.demo.model.HttpRequestData;
import com.example.callapi.demo.service.CallApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/call")
@RequiredArgsConstructor
public class CallApiController {

    private final CallApiService callApiService;

    @PostMapping
    public Object callApi(@RequestBody HttpRequestData requestData) {
        return callApiService.callApi(requestData);
    }


}