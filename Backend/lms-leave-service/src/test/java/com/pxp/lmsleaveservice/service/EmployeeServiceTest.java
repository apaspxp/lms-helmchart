package com.pxp.lmsleaveservice.service;

import com.pxp.lmsleaveservice.entity.AddressEntity;
import com.pxp.lmsleaveservice.entity.EmployeeEntity;
import com.pxp.lmsleaveservice.model.AddressModel;
import com.pxp.lmsleaveservice.model.EmployeeModel;
import com.pxp.lmsleaveservice.repo.AddressRepo;
import com.pxp.lmsleaveservice.repo.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService = new EmployeeService();

    @Mock
    private EmployeeRepo employeeRepo;

    @Mock
    private AddressRepo addressRepo;

    @Test
    public void testAddEmployee() {
        try {
            var employeeModel = employeeModelSupplier.get();

            var employeeEntity = employeeEntitySupplier.get();

            Mockito.when(employeeRepo.existsByEmailOrMobileNo(employeeModel.email(), employeeModel.mobileNo())).thenReturn(false);
            Mockito.when(employeeRepo.save(Mockito.any())).thenReturn(employeeEntity);
            Mockito.when(addressRepo.saveAll(Mockito.any())).thenReturn(List.of(addressEntitySupplier.get()));

            var expectedEmployeeModel = employeeModelSupplier.get();

            var response = employeeService.addEmployee(employeeModel);
            assertEquals("Employee added successfully", response.getMessage());
            assertEquals(expectedEmployeeModel.firstName(), response.getPayload().firstName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testUpdateEmployeeById() {
        try {
            var employeeModel = employeeModelSupplier.get();

            var employeeEntity = employeeEntitySupplier.get();
            Mockito.when(employeeRepo.findByEmployeeId(1)).thenReturn(employeeEntity);
            Mockito.when(employeeRepo.save(Mockito.any())).thenReturn(employeeEntity);
            var updatedEmployee = employeeService.updateEmployee(1, employeeModel);
            assertEquals(1, updatedEmployee.getEmployeeId());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Supplier<AddressModel> addressModelSupplier = () ->
            new AddressModel(1, "test", "test", "Test", 756100,
                    "test", "test", "test", "Test");

    private Supplier<AddressEntity> addressEntitySupplier = () ->
            new AddressEntity(1, "test", "test", "Test", 756100,
                    "test", "test", "test", "Test");

    private Supplier<EmployeeEntity> employeeEntitySupplier = () ->
            new EmployeeEntity(1, "John", "Doe", "Smith", "johndoe@example.com", 1234567890L,
                    "Developer", 2, "Active",
                    new Date(), null, null,
                    1, List.of(addressEntitySupplier.get()));

    private Supplier<EmployeeModel> employeeModelSupplier = () ->
            new EmployeeModel(1, "John", "Doe", "Smith", "johndoe@example.com", 1234567890L,
                    "Developer", 2, "Active",
                    new Date(), null, null,
                    1, List.of(addressModelSupplier.get()));

}