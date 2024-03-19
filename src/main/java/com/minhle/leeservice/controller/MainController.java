package com.minhle.leeservice.controller;

import com.minhle.leeservice.model.Route;
import com.minhle.leeservice.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {
    @Autowired
    MainService mainService;

    @PostMapping(value = "/api/routes",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Route>> sendMessage(@RequestBody Integer routeNo) {
        List<Route> routes = mainService.getRoutes();
        return ResponseEntity.ok(routes);
    }

    public ResponseEntity<Route> getRoute(@RequestBody Integer routeNo) {
        Route route = mainService.getRoutes().get(0);
        return ResponseEntity.ok(route);
    }
}
