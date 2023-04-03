package com.pxp.lmsleaveservice.controller;

import com.pxp.lmsleaveservice.entity.LeaveEntitlementEntity;
import com.pxp.lmsleaveservice.service.interfaces.ILeaveService;
import com.pxp.lmsleaveservice.service.interfaces.IHolidayCalendarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class LeaveController {

    @Autowired
    private IHolidayCalendarService holidayCalendarService;

    @Autowired
    private ILeaveService attendanceService;

    @RequestMapping(value = "uploadHolidayCalendar/{city}/{year}", method = RequestMethod.POST)
    public ResponseEntity<String> uploadHolidayCalendar(@RequestBody MultipartFile file, @PathVariable String city, @PathVariable int year){
        log.info("Entered into method uploadHolidayCalendar()");
        return new ResponseEntity<>(holidayCalendarService.saveHolidayCalender(file,city,year), HttpStatus.OK);
    }

    @RequestMapping(value = "download_blank_template", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadHolidayCalendar() throws IOException {
        Resource resource = new ClassPathResource("Templates/Holiday_calendar_template.xlsx");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + resource.getFilename())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .contentLength(resource.getFile().length())
                .body(resource);
    }

    @RequestMapping(value = "/fetchLeaveBalance/{employeeId}", method = RequestMethod.GET)
    public ResponseEntity<LeaveEntitlementEntity> fetchLeaveBalance(@PathVariable("employeeId") String employeeId){
        return new ResponseEntity<>(attendanceService.fetchLeaveBalance(employeeId), HttpStatus.OK);
    }

    @RequestMapping(value = "/applyLeave/{employeeId}", method = RequestMethod.GET)
    public ResponseEntity<LeaveEntitlementEntity> applyLeave(@PathVariable("employeeId") String employeeId){
        return new ResponseEntity<>(attendanceService.applyLeave(employeeId), HttpStatus.OK);
    }
}
