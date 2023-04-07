package com.pxp.lmsleaveservice.service;

import com.pxp.lmsleaveservice.entity.HolidayCalendarEntity;
import com.pxp.lmsleaveservice.model.HolidayCalendarModel;
import com.pxp.lmsleaveservice.repo.HolidayCalendarRepo;
import com.pxp.lmsleaveservice.service.interfaces.IHolidayCalendarService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class HolidayCalendarService implements IHolidayCalendarService {
    @Autowired
    private HolidayCalendarRepo holidayCalendarRepo;

    @Override
    @Transactional(transactionManager = "leaveServiceTransactionManager")
    public String saveHolidayCalender(MultipartFile file, String city, int year) {
        log.info("Entered into method saveHolidayCalender()");
        try {
            List<HolidayCalendarEntity> calendarList = fileReaderFunction.apply(file);
            boolean invalidData =
                    calendarList.stream()
                            .filter(e -> (!e.getCity().equalsIgnoreCase(city)) || (e.getYear() != year))
                            .findFirst()
                            .isPresent();
            if (invalidData) {
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

    @Override
    @Transactional(transactionManager = "leaveServiceTransactionManager", readOnly = true)
    public List<HolidayCalendarModel> findDistinctCityAndYear() {
        try {
            return
                    holidayCalendarRepo.findDistinctCityAndYear()
                            .stream()
                            .map(e -> new HolidayCalendarModel(e.getId(), e.getDate(), e.getDescription(), e.getCity(), e.getYear()))
                            .collect(Collectors.toList());
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] downloadHolidayCalender(String city, int year) {

        log.info("Entered into method downloadHolidayCalender()");

        Workbook workbook = new XSSFWorkbook();
        var out = new ByteArrayOutputStream();
        Sheet sheet = workbook.createSheet("Holiday Calendar");
        if (holidayCalendarRepo.findCity(city).isEmpty() || holidayCalendarRepo.findYear(year).isEmpty()) {
            log.info("Data is invalid. Please correct the year/city and re-upload the spread sheet.");
            Row row = sheet.createRow(1);
            row.createCell(0).setCellValue("No records found in the database");
        }
        List<HolidayCalendarEntity> holidayCalendarList = holidayCalendarRepo.findByCityAndYear(city, year).orElse(Collections.EMPTY_LIST);
        List<HolidayCalendarModel> holidayCalendarModelList = holidayCalendarList.stream()
                        .map(e -> new HolidayCalendarModel(Integer.valueOf(e.getId()), e.getDate(), e.getDescription(), e.getCity(), e.getYear()))
                                .collect(Collectors.toList());
        log.info("Number of records pulled from the database: " + holidayCalendarModelList.size());

        Field[] fields = HolidayCalendarModel.class.getDeclaredFields();

        Row headerRow = sheet.createRow(0);
//        Create the header row
        IntStream.range(0, fields.length)
                .boxed()
                .forEach(cellNo -> {
                    Cell cell = headerRow.createCell(cellNo);
                    CellStyle cellStyle = cell.getCellStyle();
                    if(cellStyle == null) {
                        cellStyle = cell.getSheet().getWorkbook().createCellStyle();
                    }

                    CellStyle headerStyle = workbook.createCellStyle();
                    headerStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
                    headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    Font font = workbook.createFont();
                    font.setColor(IndexedColors.WHITE.getIndex());
                    headerStyle.setFont(font);
//                    cellStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.index);
//                    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//                    cell.setCellStyle(cellStyle);
                    cell.setCellValue(StringUtils.capitalize(fields[cellNo].getName()));
                });
        headerRow.removeCell(headerRow.getCell(0));


//        Create data rows
        if (holidayCalendarModelList.isEmpty()){
            Row row = sheet.createRow(1);
            row.createCell(0).setCellValue("No records found in the database");
        } else {
            IntStream.range(0, holidayCalendarModelList.size())
                    .boxed()
                    .forEach(rowNum -> {
                        var holidayCalendarEntity = holidayCalendarModelList.get(rowNum);
                        try {
                            Row row = sheet.createRow(rowNum + 1);
//                        Collect all the field values into a list
                            var fieldValues =
                                    Stream.of(fields)
                                            .map(field -> {
                                                field.setAccessible(true);
                                                try {
                                                    return field.get(holidayCalendarEntity);
                                                } catch (IllegalAccessException e) {
                                                    throw new RuntimeException(e);
                                                }
                                            })
                                            .filter(Objects::nonNull)
                                            .collect(Collectors.toList());
//                        Create data rows
                            IntStream.range(0, fieldValues.size())
                                    .boxed()
                                    .forEach(cellNum -> row.createCell(cellNum).setCellValue(fieldValues.get(cellNum).toString()));
                            row.removeCell(row.getCell(0));
                        } catch (Exception e) {
                            throw e;
                        }
                    });
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return out.toByteArray();
    }

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

}


