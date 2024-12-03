package org.example.food_api.controller;

import org.example.food_api.models.Report;
import org.example.food_api.models.Report;
import org.example.food_api.models.Report;
import org.example.food_api.repository.ReportRepository;
import org.example.food_api.services.ReportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportService reportService;

    public static Logger logger = LoggerFactory.getLogger(ReportController.class);
    @Autowired
    private ReportRepository reportRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<Report>> listAllReport(){
        return reportService.listAllReport();
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReport(@PathVariable("id") long id) {
        return reportService.deleteReport(id);
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllReport() {
        try {
            reportRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<Report> updateReport(@RequestBody Report report) {
        return reportService.updateReport(report);
    }
    @PostMapping("/create")
    public void createReport(@RequestBody Report report) {
        reportService.save(report);
    }
}
