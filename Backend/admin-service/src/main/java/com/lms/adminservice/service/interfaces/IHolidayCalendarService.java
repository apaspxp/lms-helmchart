package com.lms.adminservice.service.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface IHolidayCalendarService {

    public String saveHolidayCalender(MultipartFile file, String city, int year);

}
