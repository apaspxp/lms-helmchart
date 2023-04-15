package com.pxp.lmsleaveservice.service;

import com.pxp.lmsleaveservice.entity.AddressEntity;
import com.pxp.lmsleaveservice.entity.EmployeeEntity;
import com.pxp.lmsleaveservice.model.AddressModel;
import com.pxp.lmsleaveservice.model.EmployeeModel;
import com.pxp.lmsleaveservice.model.ResponseModel;
import com.pxp.lmsleaveservice.repo.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Transactional(transactionManager = "leaveServiceTransactionManager")
    public ResponseModel<EmployeeModel> addEmployee(EmployeeModel employeeModel) {
        try {
            log.info("Entered into method addEmployee()");
            var employee = employeeModelToEmployeeEntityConverter.apply(employeeModel);
            if (employeeRepo.existsByEmailOrMobileNo(employeeModel.email(), employeeModel.mobileNo())) {
                log.info("Email and Phone number already exist");
                return new ResponseModel<EmployeeModel>("Employee already exists in the database", employeeModel);
            } else {
                var employeeEntity = employeeRepo.save(employee);
                var persistedEmployeeModel = employeeEntityToEmployeeModelConverter.apply(employeeEntity);
                log.info("Employee added successfully. " + persistedEmployeeModel);
                return new ResponseModel<EmployeeModel>("Employee added successfully", persistedEmployeeModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseModel<EmployeeModel>("Error adding employee. Please check the log for further details.", null);
        }
    }

    @Transactional(transactionManager = "leaveServiceTransactionManager")
    public EmployeeEntity updateEmployee(int employeeId, EmployeeModel employeeModel) {
        try {
            log.info("Entered into method updateEmployee()");
            log.info("Employee details for the employee with id {} will be updated.", employeeId);
            var employeeEntity = employeeRepo.findByEmployeeId(employeeId);
            employeeEntity.setFirstName(employeeModel.firstName());
            employeeEntity.setMiddleName(employeeModel.middleName());
            employeeEntity.setLastName(employeeModel.lastName());
            employeeEntity.setEmail(employeeModel.email());
            employeeEntity.setMobileNo(employeeModel.mobileNo());
            employeeEntity.setDesignation(employeeModel.designation());
            employeeEntity.setStatus(employeeModel.status());
            employeeEntity.setStartDate(employeeModel.startDate());
            employeeEntity.setEndDate(employeeModel.endDate());
            employeeEntity.setLastManagerChangeDate(employeeModel.lastManagerChangeDate());
            employeeEntity.setAddress(toAddressEntityList.apply(employeeModel));
            return employeeRepo.save(employeeEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public Function<AddressEntity, AddressModel> addressEntityToAddressModelConverter = addressEntity -> new AddressModel(addressEntity.getAddressId(),
            addressEntity.getAddressLine1(),
            addressEntity.getAddressLine2(),
            addressEntity.getLocality(),
            addressEntity.getPinCode(),
            addressEntity.getCity(),
            addressEntity.getState(),
            addressEntity.getCountry(),
            addressEntity.getRegion()
    );

    public Function<AddressModel, AddressEntity> addressModelToAddressEntityConverter = addressModel -> new AddressEntity(addressModel.addressId(),
            addressModel.addressLine1(),
            addressModel.addressLine2(),
            addressModel.locality(),
            addressModel.pinCode(),
            addressModel.city(),
            addressModel.state(),
            addressModel.country(),
            addressModel.region()
    );

    public Function<EmployeeEntity, List<AddressModel>> toAddressModelList = employeeEntity -> employeeEntity.getAddress()
            .stream()
            .map(addressEntityToAddressModelConverter)
            .collect(Collectors.toList());

    public Function<EmployeeModel, List<AddressEntity>> toAddressEntityList = employeeModel -> employeeModel.address()
            .stream()
            .map(addressModelToAddressEntityConverter)
            .collect(Collectors.toList());
    public Function<EmployeeEntity, EmployeeModel> employeeEntityToEmployeeModelConverter = employeeEntity -> new EmployeeModel(employeeEntity.getEmployeeId(),
            employeeEntity.getFirstName(),
            employeeEntity.getMiddleName(),
            employeeEntity.getLastName(),
            employeeEntity.getEmail(),
            employeeEntity.getMobileNo(),
            employeeEntity.getDesignation(),
            employeeEntity.getManagerId(),
            employeeEntity.getStatus(),
            employeeEntity.getStartDate(),
            employeeEntity.getEndDate(),
            employeeEntity.getLastManagerChangeDate(),
            employeeEntity.getLocationId(),
            toAddressModelList.apply(employeeEntity)
    );

    public Function<EmployeeModel, EmployeeEntity> employeeModelToEmployeeEntityConverter = employeeModel -> new EmployeeEntity(employeeModel.employeeId(),
            employeeModel.firstName(),
            employeeModel.middleName(),
            employeeModel.lastName(),
            employeeModel.email(),
            employeeModel.mobileNo(),
            employeeModel.designation(),
            employeeModel.managerId(),
            employeeModel.status(),
            employeeModel.startDate(),
            employeeModel.endDate(),
            employeeModel.lastManagerChangeDate(),
            employeeModel.locationId(),
            toAddressEntityList.apply(employeeModel)
    );
}
