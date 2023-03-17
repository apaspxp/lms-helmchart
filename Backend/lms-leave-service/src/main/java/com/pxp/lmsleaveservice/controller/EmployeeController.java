package com.pxp.lmsleaveservice.controller;

import com.pxp.lmsleaveservice.entity.EmployeeEntity;
import com.pxp.lmsleaveservice.repo.EmployeeRepo;
import com.pxp.lmsleaveservice.utility.EmailUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    EmailUtility emailUtility;

    @RequestMapping(value="getAllEmployees", method = RequestMethod.GET)
    public List<EmployeeEntity> getAllEmployee(){
        return employeeRepo.findAll();
    }

    @RequestMapping(value="sendMail", method = RequestMethod.GET)
    public String sendMail(){
        emailUtility.sendEmail("qsarthak@gmail.com", "qsarthak@gmail.com", "Test", "Hello Sarthak, hope u r doing well");
        return "mail sent";
    }
}
