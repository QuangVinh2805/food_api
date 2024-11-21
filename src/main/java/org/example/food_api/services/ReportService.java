package org.example.food_api.services;


import org.example.food_api.models.Report;
import org.example.food_api.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;

    public ResponseEntity<List<Report>> listAllReport(){
        List<Report> listReport= reportRepository.findAll();
        if(listReport.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Report>>(listReport, HttpStatus.OK);
    }
}
