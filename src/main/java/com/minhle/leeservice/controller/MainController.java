package com.minhle.leeservice.controller;

import com.minhle.leeservice.model.Employee;
import com.minhle.leeservice.model.Route;
import com.minhle.leeservice.model.request.EmployeeRequest;
import com.minhle.leeservice.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class MainController {
    @Autowired
    MainService mainService;

    @PostMapping(value = "/api/add",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> addRecords(@RequestBody EmployeeRequest employee) {
        mainService.addRecords(Collections.singletonList(employee));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<Employee>> getRecord(@RequestBody Integer employeeId) {
        List<Employee> employees = mainService.getRecord(employeeId);
        return ResponseEntity.ok(employees);
    }
}
