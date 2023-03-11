package com.pxp.lmsleaveservice.controller;

import com.pxp.lmsleaveservice.entity.EmployeeEntity;
import com.pxp.lmsleaveservice.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepo employeeRepo;

    @RequestMapping(value="getAllEmployees", method = RequestMethod.GET)
    public List<EmployeeEntity> getAllEmployee(){
        return employeeRepo.findAll();
    }
}
