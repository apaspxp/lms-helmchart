package com.pxp.lmsleaveservice.repo;

import com.pxp.lmsleaveservice.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepo extends JpaRepository<LocationEntity, Integer> {
}
