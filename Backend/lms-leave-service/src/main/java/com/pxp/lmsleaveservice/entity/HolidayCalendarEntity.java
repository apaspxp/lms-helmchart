package com.pxp.lmsleaveservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name="HOLIDAY_CALENDAR")
public class HolidayCalendarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "DATE")
    private Date date;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "CITY")
    private String city;
    @Column(name = "YEAR")
    private Integer year;

    public HolidayCalendarEntity( Date date, String description, String city, Integer year) {
        this.description = description;
        this.date = date;
        this.city = city;
        this.year = year;
    }

    public HolidayCalendarEntity(String city, Integer year) {
        this.city = city;
        this.year = year;
    }


}
