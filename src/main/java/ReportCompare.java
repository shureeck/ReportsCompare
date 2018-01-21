import java.io.File;
import java.util.ArrayList;

import  static stringconstant.ReportMessages.*;
import  static stringconstant.StringsConstants.*;

/**
 * Created by Poliakov.A on 1/18/2018.
 */
public class ReportCompare {
    public static void main(String[] args){
      //  ArrayList <File> test = new ArrayList<File>(ReportsList.getReportsList(new File("e:\\AutoTests\\Temp\\ReportCompare\\")));
//"E:\\AutoTests\\Temp\\ReportCompare\\Vertica_Redshift_apply.csv"

        File reportFile =new File("E:\\ReportExamples\\Reports\\Oracle-PostgreSQL\\Oracle_WriteToDB.csv");
        File referenceFile = new File("E:\\ReportExamples\\Reference\\Oracle-PostgreSQL\\Oracle_WriteToDB.csv");

        ArrayList<String> report = new ArrayList<String>(ReportReader.readFile(reportFile));
        ArrayList<String> reference = new ArrayList<String>(ReportReader.readFile(referenceFile));

        //Get build number
        String reportBuildNumber = ReportParser.getBuildNumber(report).trim();
        String referenceBuilNumber = ReportParser.getBuildNumber((reference));

        Logger.setReport(REFERENCE_BUILD+referenceBuilNumber+"\n"+CURRENT_BUILD+reportBuildNumber+"\n\n");

        //compare statistic by source

        CSV_Compare csv_compare = new CSV_Compare();

        if (reportFile.getName().toLowerCase().contains(APPLY) || reportFile.getName().toLowerCase().contains(WRITE)){
            Logger.setReport(STATISTIC_BY_SOURCE_XML);
            int result = csv_compare.compareStatisticBySource(report,reference,STATISTIC_BY_SOURCE_XML);

            if (result==0){
                Logger.setReport(NO_CHANGES);
            }

            Logger.setReport(GENERAL_STATISTIC);
             result = csv_compare.compareStatisticBySource(report,reference,GENERAL_STATISTIC);
            if (result==0){
                Logger.setReport(NO_CHANGES);
            }

            Logger.setReport(BY_CATEGORIES);
            result = csv_compare.compareStatisticBySource(report,reference,STATISTIC_BY_CATEGORIES);
            if (result==0){
                Logger.setReport(NO_CHANGES);
            }
            else {
                result =csv_compare.compareStatisticBySource(report,reference,GET_OBJECTS);

            }

    //    CSV_Compare comoarator = new CSV_Compare();
        }
    }
}
