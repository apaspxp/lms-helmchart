package com.pxp.lmsleaveservice.service;

import com.pxp.lmsleaveservice.entity.HolidayCalendarEntity;
import com.pxp.lmsleaveservice.model.HolidayCalendarModel;
import com.pxp.lmsleaveservice.repo.HolidayCalendarRepo;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HolidayCalendarServiceTest {
    @InjectMocks
    private HolidayCalendarService holidayCalendarService = new HolidayCalendarService();
    @Mock
    private HolidayCalendarRepo holidayCalendarRepo;

    private HolidayCalendarEntity holidayCalendarEntity;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testSaveHolidayCalendar() {
        try {
            XSSFWorkbook xssfWb1 = new XSSFWorkbook();
            xssfWb1.createSheet("S1");
            File f = File.createTempFile("temp", ".xlsx");
            try (FileOutputStream fos = new FileOutputStream(f)) {
                xssfWb1.write(fos);
            }

            MultipartFile file = new MockMultipartFile("test.xlsx", new FileInputStream(f));


            List<HolidayCalendarEntity> calendarList = Arrays.asList(
                    new HolidayCalendarEntity(new Date(), "Test", "Test", 1)
            );
            Mockito.doNothing().when(holidayCalendarRepo).deleteByDate(Mockito.any());
            when(holidayCalendarRepo.saveAll(Mockito.any())).thenReturn(calendarList);
            assertEquals("Upload successful.", holidayCalendarService.saveHolidayCalender(file, "Test", 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveHolidayCalendarException() {
        try {
            XSSFWorkbook xssfWb1 = new XSSFWorkbook();
            xssfWb1.createSheet("S1");
            File f = File.createTempFile("temp", ".xlsx");
            try (FileOutputStream fos = new FileOutputStream(f)) {
                xssfWb1.write(fos);
            }

            MultipartFile file = new MockMultipartFile("test.xlsx", new FileInputStream(f));


            List<HolidayCalendarEntity> calendarList = Arrays.asList(
                    new HolidayCalendarEntity(new Date(), "Test", "Test", 1)
            );
            Mockito.doThrow(new RuntimeException("Test")).when(holidayCalendarRepo).deleteByDate(Mockito.any());
            assertEquals("Test", holidayCalendarService.saveHolidayCalender(file, "Test", 1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFindDistinctCityAndYear() {
        try {
            List<HolidayCalendarEntity> entityList = new ArrayList<>();
            when(holidayCalendarRepo.findDistinctCityAndYear()).thenReturn(entityList);
            List<HolidayCalendarModel> actualList = holidayCalendarService.findDistinctCityAndYear();
            assertEquals(entityList.size(), actualList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    void testDownloadHolidayCalendar() {
        try {
            String city = "New York";
            int year = 2022;
            List<HolidayCalendarEntity> holidayCalendarList = Arrays.asList(
                    new HolidayCalendarEntity(1, new Date(), "New Year's Day", city, year),
                    new HolidayCalendarEntity(2, new Date(), "Christmas Day", city, year)
            );
            when(holidayCalendarRepo.findCity(city)).thenReturn(Optional.of(city));
            when(holidayCalendarRepo.findYear(year)).thenReturn(Optional.of(year));
            when(holidayCalendarRepo.findByCityAndYear(city, year)).thenReturn(Optional.of(holidayCalendarList));
            byte[] result = holidayCalendarService.downloadHolidayCalender(city, year);
            Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(result));
            Sheet sheet = workbook.getSheetAt(0);
            assertEquals(2, sheet.getLastRowNum());
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}