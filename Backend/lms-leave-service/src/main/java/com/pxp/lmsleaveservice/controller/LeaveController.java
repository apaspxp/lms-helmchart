package com.pxp.lmsleaveservice.controller;

import com.pxp.lmsleaveservice.model.HolidayCalendarModel;
import com.pxp.lmsleaveservice.repo.HolidayCalendarRepo;
import com.pxp.lmsleaveservice.service.HolidayCalendarService;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class LeaveController {

    @Autowired
    private HolidayCalendarService holidayCalendarService;
    @Autowired
    private HolidayCalendarRepo holidayCalendarRepo;

    @RequestMapping(value = "uploadHolidayCalendar/{city}/{year}", method = RequestMethod.POST)
    public ResponseEntity<String> uploadHolidayCalendar(@RequestBody MultipartFile file, @PathVariable String city, @PathVariable int year) {
        log.info("Entered into method uploadHolidayCalendar()");
        return new ResponseEntity<>(holidayCalendarService.saveHolidayCalender(file, city, year), HttpStatus.OK);
    }

    @RequestMapping(value = "download_blank_template", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadHolidayCalendarTemplate() throws IOException {
        Resource resource = new ClassPathResource("Templates/Holiday_calendar_template.xlsx");
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + resource.getFilename())
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .contentLength(resource.getFile().length())
                .body(resource);
    }

    @RequestMapping(value = "findDistinctCityAndYear", method = RequestMethod.GET)
    public List<HolidayCalendarModel> distinctCityAndYear() {
        return holidayCalendarService.findDistinctCityAndYear();
    }

    @RequestMapping(value = "/holiday-calendar/download/{city}/{year}", method = RequestMethod.GET)
    public ResponseEntity<Resource> downloadHolidayCalendar(@PathVariable @NotBlank String city, @PathVariable @NotBlank @Positive int year) {
        log.info("Entered into method downloadHolidayCalendar()");
        byte[] data = holidayCalendarService.downloadHolidayCalender(city, year);

        ByteArrayResource resource = new ByteArrayResource(data);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=Holiday_Calendar_" + year + "_" + city + ".xlsx")
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .contentLength(data.length)
                .body(resource);
    }
}





