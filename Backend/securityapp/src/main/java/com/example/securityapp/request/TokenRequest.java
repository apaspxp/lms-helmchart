package com.example.securityapp.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class TokenRequest {
    private String path;
    private String token;
    private String method;
    private String moduleName;
    private String serviceName;
    private Map<String, String> headers;
}
