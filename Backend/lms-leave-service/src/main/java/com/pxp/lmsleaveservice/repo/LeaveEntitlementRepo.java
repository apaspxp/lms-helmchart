package com.pxp.lmsleaveservice.repo;

import com.pxp.lmsleaveservice.entity.LeaveEntitlementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveEntitlementRepo extends JpaRepository <LeaveEntitlementEntity, String> {

}
