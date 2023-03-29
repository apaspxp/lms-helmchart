package com.lms.attendanceservice.repo;

import com.lms.attendanceservice.entity.LeaveEntitlementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveEntitlementRepo extends JpaRepository<LeaveEntitlementEntity, Integer> {

    public void test();

}
