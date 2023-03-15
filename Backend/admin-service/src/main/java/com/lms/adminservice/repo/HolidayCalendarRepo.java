package com.lms.adminservice.repo;


import com.lms.adminservice.entity.HolidayCalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface HolidayCalendarRepo extends JpaRepository<HolidayCalendarEntity, Integer> {

    @Modifying
    @Query(value = "delete from HolidayCalendarEntity where date > :date")
    public void deleteByDate(Date date);
}
