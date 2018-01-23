import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import  static stringconstant.ReportMessages.*;
import  static stringconstant.StringsConstants.*;

/**
 * Created by Poliakov.A on 1/18/2018.
 */
public class ReportCompare {
    public static void main(String[] args){
      //  ArrayList <File> test = new ArrayList<File>(ReportsList.getReportsList(new File("e:\\AutoTests\\Temp\\ReportCompare\\")));
//"E:\\AutoTests\\Temp\\ReportCompare\\Vertica_Redshift_apply.csv",

        String reportsPath="e:\\AutoTests\\Reports\\Main2206\\reports\\";
        String referencePath="e:\\AutoTests\\Reports\\Main2197\\reports\\";

        ArrayList <File> reportsList = new ArrayList<>(ReportsList.getReportsList(new File(reportsPath)));
        ArrayList <File> referenceList = new ArrayList<>(ReportsList.getReportsList(new File(referencePath)));

        ArrayList<File> reportsByPair = ReportsList.getReportsForPair(reportsList);

        Logger.setReport(reportsByPair.get(0).getParentFile().getPath());
        CSV_Compare csv_compare = new CSV_Compare();

        int i = 0;
        while (i<reportsByPair.size()){
            String path = reportsByPair.get(i).getPath().substring(referencePath.length());

            File referenceFile = referenceList.stream().filter((p)->p.getPath().endsWith(path)).findFirst().get();
            File reportFile = reportsByPair.get(i);

            ArrayList<String> report = new ArrayList<String>(ReportReader.readFile(reportFile));
            ArrayList<String> reference = new ArrayList<String>(ReportReader.readFile(referenceFile));

            Logger.setReport(MARKER+reportFile.getName());

            if (report.stream().anyMatch((p)->p.trim().equalsIgnoreCase(APPLY))){
                csv_compare.compareApplyReports(report,reference);
            }

            else if (report.stream().anyMatch((p)->p.trim().equalsIgnoreCase(CONVERSION))){
                csv_compare.compareConversionReports(report,reference);
            }

            else if (report.stream().anyMatch((p)->p.trim().equalsIgnoreCase(AI) || p.equalsIgnoreCase(ACTION_ITEMS))){
                csv_compare.compareErrorReports(report,reference);
            }

            else if (report.stream().anyMatch((p)->p.trim().equalsIgnoreCase(ERROR))){
                csv_compare.compareAIReports(report,reference);
            }
            else {
                Logger.setReport(COULD_NOT_IDENTIFY_REPORT_TYPE);
            }
            referenceList.removeIf((p)->p.getPath().endsWith(path));
            i++;
        }
        Logger.setReport(SEPARATOR);

       //File reportFile =new File("E:\\ReportExamples\\Reports\\Oracle-PostgreSQL\\Oracle_WriteToDB.csv");
       //File referenceFile = new File("E:\\ReportExamples\\Reference\\Oracle-PostgreSQL\\Oracle_WriteToDB.csv");

       // File reportFile =new File("E:\\AutoTests\\Reports\\Main2211\\reports\\DB2_MySQL\\DB2_MySQL_ReportTest.csv");
       // File referenceFile = new File("E:\\AutoTests\\Reports\\Main2136\\reports\\DB2_MySQL\\DB2_MySQL_ReportTest.csv");




        //Get build number
      //  String reportBuildNumber = ReportParser.getBuildNumber(report).trim();
      //  String referenceBuilNumber = ReportParser.getBuildNumber((reference));

     //   Logger.setReport(REFERENCE_BUILD+referenceBuilNumber+"\n"+CURRENT_BUILD+reportBuildNumber+"\n");

        //Reports Compare





    }
}
