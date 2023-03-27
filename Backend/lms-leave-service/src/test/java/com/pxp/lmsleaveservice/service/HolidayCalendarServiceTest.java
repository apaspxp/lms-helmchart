package com.pxp.lmsleaveservice.service;

import com.pxp.lmsleaveservice.entity.HolidayCalendarEntity;
import com.pxp.lmsleaveservice.repo.HolidayCalendarRepo;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HolidayCalendarServiceTest {
    @InjectMocks
    private HolidayCalendarService holidayCalendarService = new HolidayCalendarService();
    @Mock
    private HolidayCalendarRepo holidayCalendarRepo;

    @BeforeEach
    void setUp() {
    }
    @Test
    public void testSaveHolidayCalendar(){
        try{
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
            Mockito.when(holidayCalendarRepo.saveAll(Mockito.any())).thenReturn(calendarList);
            assertEquals("Upload successful.", holidayCalendarService.saveHolidayCalender(file, "Test", 1));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testSaveHolidayCalendarException(){
        try{
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}