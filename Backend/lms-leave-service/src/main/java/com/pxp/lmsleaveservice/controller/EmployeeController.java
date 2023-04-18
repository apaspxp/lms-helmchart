package com.pxp.lmsleaveservice.controller;

import com.pxp.lmsleaveservice.entity.EmployeeEntity;
import com.pxp.lmsleaveservice.model.EmployeeModel;
import com.pxp.lmsleaveservice.model.ResponseModel;
import com.pxp.lmsleaveservice.repo.EmployeeRepo;
import com.pxp.lmsleaveservice.service.EmployeeService;
import com.pxp.lmsleaveservice.utility.EmailUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class EmployeeController {

    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    EmailUtility emailUtility;

    @RequestMapping(value="getAllEmployees", method = RequestMethod.GET)
    public List<EmployeeEntity> getAllEmployee(){
        return employeeRepo.findAll();
    }

    @RequestMapping(value="sendMail", method = RequestMethod.GET)
    public String sendMail(@RequestParam("body") String body){
        emailUtility.sendEmail("qsarthak@gmail.com", "qsarthak@gmail.com", "Test", body);
        return "mail sent";
    }
    @RequestMapping(value = "/addEmployee", method = RequestMethod.POST)
    public ResponseEntity<ResponseModel<EmployeeModel>> addEmployee(@RequestBody EmployeeModel employeeModel){
        return new ResponseEntity<>(employeeService.addEmployee(employeeModel), HttpStatus.OK);
    }
    @RequestMapping(value = "/updateEmployee/{employeeId}", method = RequestMethod.PUT)
    public ResponseEntity<EmployeeEntity> updateEmployeeById(@PathVariable int employeeId, @RequestBody EmployeeModel employeeModel){
        return new ResponseEntity<>(employeeService.updateEmployee(employeeId,employeeModel), HttpStatus.OK);
    }
}
