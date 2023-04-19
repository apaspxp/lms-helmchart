package com.lms.profileservice.controller;

import com.lms.profileservice.service.interfaces.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping(value = "/testProfileService", method = RequestMethod.GET)
    public ResponseEntity<String> testProfileService(@PathVariable("employeeId") String employeeId){
        return new ResponseEntity<>("sarthak", HttpStatus.OK);
    }
}





