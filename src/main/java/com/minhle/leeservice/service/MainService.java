package com.minhle.leeservice.service;

import com.minhle.leeservice.model.Route;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainService {
    public List<Route> getRoutes() {
        return new ArrayList<>();
    }
}
