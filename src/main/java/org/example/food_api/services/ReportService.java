package org.example.food_api.services;


import org.example.food_api.models.Report;
import org.example.food_api.models.Report;
import org.example.food_api.models.User;
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
    public void save(Report report){
        reportRepository.save(report);
    }
    public ResponseEntity<Report> updateReport(Report report){
        Long reportId = reportRepository.findIdByReportId(report.getId());
        if (reportId == null){
            String message = "report id not found";
            System.out.println(message + reportId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        reportRepository.save(report);
        return new ResponseEntity(report, HttpStatus.OK);
    }


    public ResponseEntity<String> deleteReport(Long id){
        Long reportId = reportRepository.findIdByReportId(id);
        if (reportId == null){
            String message = "report id not found";
            System.out.println(message + reportId);
            return new ResponseEntity(message, HttpStatus.FORBIDDEN);

        }
        reportRepository.deleteById(reportId);
        return new ResponseEntity(HttpStatus.OK);
    }
}
