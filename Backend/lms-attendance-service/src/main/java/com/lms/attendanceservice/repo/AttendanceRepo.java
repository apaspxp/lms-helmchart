package com.lms.attendanceservice.repo;

import com.lms.attendanceservice.entity.AttendanceEntity;
import com.lms.attendanceservice.entity.AttendanceId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AttendanceRepo extends JpaRepository<AttendanceEntity, AttendanceId> {

    AttendanceEntity findByEmployeeIdAndDate(String empId, Date date);

}
