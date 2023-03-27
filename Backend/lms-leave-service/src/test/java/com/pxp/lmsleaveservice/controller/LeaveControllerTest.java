package com.pxp.lmsleaveservice.controller;

import com.pxp.lmsleaveservice.service.HolidayCalendarService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LeaveControllerTest {

    @InjectMocks
    private LeaveController leaveController = new LeaveController();
    @Mock
    private HolidayCalendarService holidayCalendarService;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testUploadHolidayCalendar() {
        try {
            XSSFWorkbook xssfWb1 = new XSSFWorkbook();
            xssfWb1.createSheet("S1");
            File f = File.createTempFile("temp", ".xlsx");
            try (FileOutputStream fos = new FileOutputStream(f)) {
                xssfWb1.write(fos);
            }

            MultipartFile file = new MockMultipartFile("test.xlsx", new FileInputStream(f));
            when(holidayCalendarService.saveHolidayCalender(file, "Test", 1)).thenReturn("Test");
            assertEquals("Test", leaveController.uploadHolidayCalendar(file, "Test", 1).getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDownloadTemplate() throws IOException {
        assertEquals("Holiday_calendar_template.xlsx", leaveController.downloadHolidayCalendar().getBody().getFilename());
    }
}