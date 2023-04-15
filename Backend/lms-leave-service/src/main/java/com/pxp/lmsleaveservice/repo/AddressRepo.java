package com.pxp.lmsleaveservice.repo;

import com.pxp.lmsleaveservice.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<AddressEntity, Integer> {
}
