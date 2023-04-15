package com.pxp.lmsleaveservice.repo;

import com.pxp.lmsleaveservice.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Integer> {

    public EmployeeEntity findByEmployeeId(@Param("employeeId") Integer employeeId);

    public boolean existsByEmailOrMobileNo(@Param("email") String email, @Param("mobileNo") long mobileNo);
}
