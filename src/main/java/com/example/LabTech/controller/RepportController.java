package com.example.LabTech.controller;

import com.example.LabTech.entite.enums.Status;
import com.example.LabTech.entite.enums.Status_Analyse;
import com.example.LabTech.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.Date;


@RestController
public class RepportController {
    @Autowired
    private ReportService service;
    @GetMapping("/report/{id}/{format}")
    public String generateReport(@PathVariable String format , @PathVariable long id ) throws FileNotFoundException, JRException {
        return service.generateAnalyseReport(format,id);
    }
    @GetMapping("/report/{format}/{date_debut}/{date_end}")
    public String generateReportAnalyseByDate(@PathVariable String format ,
                                              @PathVariable Date date_debut ,
                                              @PathVariable Date date_end )
            throws FileNotFoundException, JRException {
        return service.exportReportAnalyseByDate(format,date_debut,date_end);
    }
    @GetMapping("/report/{status}/{format}")
    public String generateReportAnalyseByStatus(@PathVariable String format , @PathVariable Status_Analyse status) throws FileNotFoundException, JRException {
        return service.exportReportAnalyseByStatus(format,status);
    }
}
