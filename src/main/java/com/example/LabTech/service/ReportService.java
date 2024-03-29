package com.example.LabTech.service;

import com.example.LabTech.DTO.RapportDto;
import com.example.LabTech.entite.*;
import com.example.LabTech.entite.enums.Status_Analyse;
import com.example.LabTech.repository.AnalyseRepository;
import com.example.LabTech.repository.EchantillonRepository;
import com.example.LabTech.repository.PatientRepository;
import com.example.LabTech.service.interfaces.IReportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

@Service
public class ReportService implements IReportService {

    @Autowired
    private AnalyseRepository analyseRepository;
    @Autowired
    private PatientRepository patientRepository ;
    @Autowired
    private EchantillonRepository echantillonRepository ;

    @Override
    public String generateAnalyseReport(String reportFormat, long id) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\asus\\Desktop\\hebrnate anotation";
        Analyse analyse = analyseRepository.getReferenceById(id);

        if (analyse != null) {
            // Create a list to store the data for the report
            List<ReportData> reportDataList = new ArrayList<>();
            ReportData reportData = new ReportData() ;
            reportData.setAnalyseName(analyse.getName());
            for (Type_Analyse typeAnalyse : analyse.getTypeAnalyses()) {
              reportData.setTypeAnalyseName(typeAnalyse.getName());
                for (Test_analyse testAnalyse : typeAnalyse.getTestAnalyses()) {
                    // Create a ReportData object for the test
                            reportData.setTestName(testAnalyse.getEnorm().getName());
                            reportData.setPlageNormaleMax((double) testAnalyse.getEnorm().getPlage_normale_max());
                            reportData.setPlageNormaleMin((double) testAnalyse.getEnorm().getPlage_normale_min());
                            reportData.setResultat(String.valueOf(testAnalyse.getResultat()));
                            reportData.setUniteMesure( testAnalyse.getEnorm().getUnite_mesure());
                            reportData.setStatus(String.valueOf(testAnalyse.getStatus())) ;
                            reportDataList.add(reportData);

                }
            }

            // Load file and compile it
            File file = ResourceUtils.getFile("classpath:analyse.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());

            // Create JRBeanCollectionDataSource with the list of ReportData
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportDataList);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "STRAW HATS");

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            if (reportFormat.equalsIgnoreCase("html")) {
                JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\analyse_report.html");
            } else if (reportFormat.equalsIgnoreCase("pdf")) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\analyse_report.pdf");
            }

            return "Report generated in path: " + path;
        }

        return null;
    }

    @Override
    public String exportReportAnalyseByDate(String reportFormat , Date dateStart , Date dateEnd) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\asus\\Desktop\\hebrnate anotation";
        List<Analyse> analyses = analyseRepository.findByDateDebutBetween(dateStart,dateEnd);
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:analysebydate.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(analyses);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "STRAW HATS");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employees.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\analysebydate.pdf");
        }

        return "report generated in path : " + path;
    }



    @Override
    public String exportReportAnalyseByStatus(String reportFormat , Status_Analyse statusAnalyse) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\asus\\Desktop\\hebrnate anotation";
        List<Analyse> analyses = analyseRepository.getAnalyseByStatus(statusAnalyse);
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:analysebystatus.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(analyses);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "STRAW HATS");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employees.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\analysebystatus.pdf");
        }

        return "report generated in path : " + path;
    }

    @Override
    public String exportReportAnalyseByPatent(String reportFormat , long id) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\asus\\Desktop\\hebrnate anotation";
        Optional<Patient> patient = patientRepository.findById(id);
       List<Echantillon> echantillons = echantillonRepository.getEchantillonByPatient(patient.get());
       List<Analyse> analyses = new ArrayList<>();
       for (Echantillon echantillon :echantillons){
           List<Analyse> analyses1 = analyseRepository.getAnalyseByEchantillon(echantillon);
           analyses.addAll(analyses1);
       }
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:analysebystatus.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(analyses);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "STRAW HATS");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employees.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\analysePatien.pdf");
        }

        return "report generated in path : " + path;
    }


    public String exportRepport(String reportFormat , long id) throws FileNotFoundException, JRException {
        String path = "C:\\Users\\asus\\Desktop\\hebrnate anotation";
        List<Object[]> results = analyseRepository.getAnalysisReport(id);
        List<RapportDto> rapportDtos = new ArrayList<>();
        for (Object[] result : results) {
            RapportDto rapportDto = new RapportDto(result);
            rapportDtos.add(rapportDto);
        }
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:analyse.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(rapportDtos);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "STRAW HATS");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\analyse.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\analyse.pdf");
        }

        return "report generated in path : " + path;
    }
    }



