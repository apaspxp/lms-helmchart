package com.pxp.lmsleaveservice.service.interfaces;

import com.pxp.lmsleaveservice.entity.EmployeeEntity;
import com.pxp.lmsleaveservice.model.EmployeeModel;
import com.pxp.lmsleaveservice.model.HolidayCalendarModel;
import com.pxp.lmsleaveservice.model.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IHolidayCalendarService {

    public String saveHolidayCalender(MultipartFile file, String city, int year);

    public List<HolidayCalendarModel> findDistinctCityAndYear();

    public byte[] downloadHolidayCalender(String city, int year);
//    public ResponseModel<EmployeeModel> addEmployee(EmployeeEntity employee);
//    public EmployeeEntity updateEmployee(Integer employeeId, EmployeeEntity employee);
}
