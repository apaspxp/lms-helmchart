package com.pxp.lmsleaveservice.controller;

import com.pxp.lmsleaveservice.service.HolidayCalendarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class LeaveController {

    @Autowired
    private HolidayCalendarService holidayCalendarService;

    @RequestMapping(value = "uploadHolidayCalendar/{city}/{year}", method = RequestMethod.POST)
    public ResponseEntity<String> uploadHolidayCalendar(@RequestBody MultipartFile file, @PathVariable String city, @PathVariable int year){
        log.info("Entered into method uploadHolidayCalendar()");
        return new ResponseEntity<>(holidayCalendarService.saveHolidayCalender(file,city,year), HttpStatus.OK);
    }
}
