package com.pxp.lmsleaveservice.repo;


import com.pxp.lmsleaveservice.entity.HolidayCalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface HolidayCalendarRepo extends JpaRepository<HolidayCalendarEntity, Integer> {

    @Modifying
    @Query(value = "delete from HolidayCalendarEntity where date > :date")
    public void deleteByDate(Date date);

    @Query(value = "select distinct new com.pxp.lmsleaveservice.entity.HolidayCalendarEntity(city, year) from HolidayCalendarEntity")
    public List<HolidayCalendarEntity> findDistinctCityAndYear();

    public Optional<List<HolidayCalendarEntity>> findByCityAndYear(String city, int year);

    @Query(value = "select distinct city from HolidayCalendarEntity where city = :city")
    public Optional<String> findCity(String city);

    @Query(value = "select distinct year from HolidayCalendarEntity where year = :year")
    public Optional<Integer> findYear(int year);
}
