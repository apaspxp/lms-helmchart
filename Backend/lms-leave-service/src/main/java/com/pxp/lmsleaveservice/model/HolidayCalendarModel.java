package com.pxp.lmsleaveservice.model;

import java.util.Date;

public record HolidayCalendarModel(int id, Date date, String description, String city, Integer year) {
}
