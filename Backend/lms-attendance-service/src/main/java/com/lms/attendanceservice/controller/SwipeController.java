package com.lms.attendanceservice.controller;

import com.lms.attendanceservice.responses.Response;
import com.lms.attendanceservice.responses.SwipeResponse;
import com.lms.attendanceservice.service.interfaces.ISwipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class SwipeController {

    @Autowired
    private ISwipeService swipeService;

    @RequestMapping(value = "/swipe", method = RequestMethod.POST)
    public ResponseEntity<Response<SwipeResponse>> swipe(@RequestParam("option") String swipeOption){
        log.info("Inside swipe controller");
        ResponseEntity<Response<SwipeResponse>> responseEntity = new ResponseEntity<>(swipeService.swipe(swipeOption), HttpStatus.OK);
        log.info("Exit swipe controller");
        return responseEntity;
    }

}





