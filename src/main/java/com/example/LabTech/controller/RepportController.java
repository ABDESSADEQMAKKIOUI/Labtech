package com.example.LabTech.controller;

import com.example.LabTech.DTO.PatientDto;
import com.example.LabTech.entite.Patient;
import com.example.LabTech.entite.enums.Status;
import com.example.LabTech.entite.enums.Status_Analyse;
import com.example.LabTech.service.PatientService;
import com.example.LabTech.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.Date;


@RestController
public class RepportController {
    @Autowired
    private ReportService service;
    @Autowired
    private PatientService patientService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping("/report/{id}/{format}")
    public String generateReport(@PathVariable String format , @PathVariable long id ) throws FileNotFoundException, JRException {
        return service.exportRepport(format,id);
    }
    @GetMapping("/report/{format}/{date_debut}/{date_end}")
    public String generateReportAnalyseByDate(@PathVariable String format ,
                                              @PathVariable Date date_debut ,
                                              @PathVariable Date date_end )
            throws FileNotFoundException, JRException {
        return service.exportReportAnalyseByDate(format,date_debut,date_end);
    }
    @GetMapping("/report/format/status")
    public String generateReportAnalyseByStatus(@RequestParam("reportFormat") String reportFormat, @RequestParam("status") String status) throws FileNotFoundException, JRException {
        return service.exportReportAnalyseByStatus(reportFormat, Status_Analyse.valueOf(status));
    }
    @GetMapping("/report/patient/{id}/{format}")
    public String exportReportAnalyseByPatent(@PathVariable String format , @PathVariable long id ) throws FileNotFoundException, JRException {

        return service.exportReportAnalyseByPatent(format,id);
    }
}
