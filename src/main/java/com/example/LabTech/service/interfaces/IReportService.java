package com.example.LabTech.service.interfaces;

import com.example.LabTech.entite.Patient;
import com.example.LabTech.entite.enums.Status_Analyse;
import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;
import java.util.Date;

public interface IReportService {
   String exportReportAnalyseByStatus(String reportFormat , Status_Analyse statusAnalyse)throws FileNotFoundException, JRException;
    String exportReportAnalyseByDate(String reportFormat , Date dateStart , Date dateEnd)throws FileNotFoundException, JRException;
    String generateAnalyseReport(String reportFormat, long id) throws FileNotFoundException, JRException;
    String exportReportAnalyseByPatent(String reportFormat , long id) throws FileNotFoundException, JRException ;
}
