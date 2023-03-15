package com.lms.adminservice.service;

import com.lms.adminservice.entity.HolidayCalendarEntity;
import com.lms.adminservice.repo.HolidayCalendarRepo;
import com.lms.adminservice.service.interfaces.IHolidayCalendarService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class HolidayCalendarService implements IHolidayCalendarService {

    @Autowired
    private HolidayCalendarRepo holidayCalendarRepo;

    public Function<MultipartFile, List<HolidayCalendarEntity>> fileReaderFunction = file -> {
        log.info("Function fileReaderFunction was invoked");
        log.info("Name of the file is: " + file.getOriginalFilename());
        try {
            InputStream inputStream = file.getInputStream();

            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);

            log.info("Total number of rows including header: " + sheet.getPhysicalNumberOfRows());

            return
                    StreamSupport.stream(sheet.spliterator(), false)
                            .skip(1)
                            .filter(row -> row.getPhysicalNumberOfCells() == 4)
                            .map(row -> {
                                return new HolidayCalendarEntity(row.getCell(0).getDateCellValue(),
                                        row.getCell(1).getStringCellValue(),
                                        row.getCell(2).getStringCellValue(),
                                        (int) row.getCell(3).getNumericCellValue());
                            })
                            .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    };

    @Override
    @Transactional(transactionManager = "leaveServiceTransactionManager")
    public String saveHolidayCalender(MultipartFile file,String city,int year) {
        log.info("Entered into method saveHolidayCalender()");
        try {
            List<HolidayCalendarEntity> calendarList = fileReaderFunction.apply(file);
            boolean invalidData =
            calendarList.stream()
                            .filter(e -> (!e.getCity().equalsIgnoreCase(city)) || (e.getYear() != year))
                                    .findFirst()
                                            .isPresent();
            if (invalidData){
                return "Data is invalid. Please correct the year/city and re-upload the spread sheet.";
            }
            log.info("Size of calendarList: " + calendarList.size());
//            Delete all the records from the database table whose date is greater than today's date
            Date currentDate = new Date();
            holidayCalendarRepo.deleteByDate(currentDate);
//            Save only those records whose date is greater than today's date.
            var filteredList = calendarList.stream().
                    filter(list -> list.getDate().after(new Date())).
                    collect(Collectors.toList());
            log.info(filteredList.toString());
            holidayCalendarRepo.saveAll(filteredList);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "Upload successful.";
    }

}

