package com.minhle.leeservice.service;

import com.minhle.leeservice.model.Employee;
import com.minhle.leeservice.model.Route;
import com.minhle.leeservice.model.request.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MainService {

    public void addRecords(List<EmployeeRequest> employeeRequests) {
        employeeRequests.forEach(e -> {});
        return;
    }

    public List<Employee> getRecord(Integer employeeId) {
        return new ArrayList<>();
    }
}
