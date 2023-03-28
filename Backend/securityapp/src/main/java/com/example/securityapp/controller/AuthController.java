package com.example.securityapp.controller;

import com.example.securityapp.request.TokenRequest;
import com.example.securityapp.response.TokenResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private static  final Logger log = LoggerFactory.getLogger(AuthController.class);

    @RequestMapping(value = "/verify", method = RequestMethod.POST)
    public ResponseEntity<TokenResponse> verify(@RequestBody TokenRequest tokenRequest) {
        TokenResponse tokenResponse = new TokenResponse();
        log.info("Inside verify method");
        String token = tokenRequest.getToken();

        if(null!=token && token.equals("sarthak")){
            tokenResponse.setAuthenticated(true);
        }
        if(null!=tokenRequest.getServiceName() && tokenRequest.getServiceName().equals("getProfile")
                && null!=tokenRequest.getModuleName() && tokenRequest.getModuleName().equals("backend-app-2")
                && tokenResponse.isAuthenticated()){
            tokenResponse.setAuthorized(true);
        }
        if(token==null && null!=tokenRequest.getServiceName() && tokenRequest.getServiceName().equals("login")){
            tokenResponse.setAuthenticated(true);
            tokenResponse.setAuthorized(true);
        }


        if(!tokenResponse.isAuthenticated()){
            return new ResponseEntity<>(tokenResponse, HttpStatus.UNAUTHORIZED);
        } else if(!tokenResponse.isAuthorized()){
            return new ResponseEntity<>(tokenResponse, HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok(tokenResponse);
    }

}
