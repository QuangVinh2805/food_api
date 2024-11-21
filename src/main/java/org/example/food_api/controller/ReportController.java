package org.example.food_api.controller;

import org.example.food_api.models.Report;
import org.example.food_api.services.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportService reportService;

    public static Logger logger = LoggerFactory.getLogger(ReportController.class);

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Report>> listAllReport(){
        return reportService.listAllReport();
    }
}
