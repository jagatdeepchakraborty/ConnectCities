package com.mc.connectcities.controller;

import com.mc.connectcities.logic.ConnectCitiesLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectCitiesController {
    ConnectCitiesLogic connectCitiesLogic;

    @Autowired
    public ConnectCitiesController(ConnectCitiesLogic connectCitiesLogic) {
        this.connectCitiesLogic = connectCitiesLogic;
    }

    @RequestMapping(value = "/connected", method = RequestMethod.GET)
    public String connected(@RequestParam(value = "origin") String origin,
                              @RequestParam(value = "destination") String destination) {
        return connectCitiesLogic.connected(origin, destination);
    }
}
