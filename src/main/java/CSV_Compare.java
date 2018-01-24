import java.io.File;
import java.util.ArrayList;

import static stringconstant.StringsConstants.*;
import static stringconstant.ReportMessages.*;

/**
 * Created by Poliakov.A on 1/19/2018.
 */
public class CSV_Compare {

    private int compareStatisticBySource(ArrayList<String> report, ArrayList<String>reference, String statisticType){
        //Get collections with statistic y source
        ArrayList<String>tempReport = new ArrayList<>(ReportParser.getStatistic(report, statisticType ));
        ArrayList<String>tempReference = new ArrayList<>(ReportParser.getStatistic(reference, statisticType));
        //Compare statistic
        int result = compareReport(tempReport,tempReference);

        return result;
    }// compareStatisticBySource

    private int compareReport(ArrayList<String> report, ArrayList<String>reference){
        int i=0;
        int count =0;
        while (report.size()>i){
            String temp = report.get(i);
            if (reference.stream().noneMatch((p)->p.equalsIgnoreCase(temp)))
            {
               //will be changed to report or buffer
                Logger.setReport(report.get(i));
                count++;
            }
            i++;
        }
        return count;
    }//compareReport

    private int compareStatisticBySource(ArrayList<String> report, ArrayList<String>reference, String statisticType, int numberFailedObjects){
        //Get collections with statistic y source
        ArrayList<String>tempReport = new ArrayList<>(ReportParser.getStatistic(report, statisticType ));
        ArrayList<String>tempReference = new ArrayList<>(ReportParser.getStatistic(reference, statisticType));
        //Compare statistic
        int result = compareReport(tempReport,tempReference,  numberFailedObjects);

        return result;
    }// compareStatisticBySource

    private int compareReport(ArrayList<String> report, ArrayList<String>reference, int numberFailedObjects){
        int i=0;
        int count =0;
        while (report.size()>i && count<=numberFailedObjects){
            String temp = report.get(i);
            if (reference.stream().noneMatch((p)->p.equalsIgnoreCase(temp)))
            {
                //will be changed to report or buffer
                Logger.setReport(report.get(i));
                count++;
            }
            i++;
        }
        return count;
    }//compareReport

    public void compareApplyReports (ArrayList<String> report, ArrayList<String> reference, int numberFailed){
        int result;
        //Get statistic by source
        Logger.setReport(MARKER_2+STATISTIC_BY_SOURCE_XML);
        result = compareStatisticBySource(report,reference,STATISTIC_BY_SOURCE_XML);
        if (result==0){
            Logger.setReport(NO_CHANGES);
        }
        //Get general statistic
        Logger.setReport(MARKER_2+GENERAL_STATISTIC);
        result = compareStatisticBySource(report,reference,GENERAL_STATISTIC);
        if (result==0){
            Logger.setReport(NO_CHANGES);
        }
        //Get statistic by categories
        Logger.setReport(MARKER_2+BY_CATEGORIES);
        result = compareStatisticBySource(report,reference,STATISTIC_BY_CATEGORIES);
        if (result==0){
            Logger.setReport(NO_CHANGES);
        }
        else {
            result=compareStatisticBySource(report,reference,GET_OBJECTS,numberFailed);
            if (result==0){
                Logger.setReport(NO_DIFFERENCE_OBJECT_STATUS);
                Logger.setReport(MANUAL_ANALYS_NECESSARY);
            }
        }
    }//compareApplyReports

    public void compareConversionReports (ArrayList<String> report, ArrayList<String> reference, int numberFailed){
        int result;

        Logger.setReport(MARKER_2+GENERAL_STATISTIC);
        result = compareStatisticBySource(report,reference,GENERAL_STATISTIC);
        if (result==0){
            Logger.setReport(NO_CHANGES);
        }

        Logger.setReport(MARKER_2+BY_CATEGORIES);
        result = compareStatisticBySource(report,reference,STATISTIC_BY_CATEGORIES);
        if (result==0){
            Logger.setReport(NO_CHANGES);
        }
        else {
            result=compareStatisticBySource(report,reference,GET_OBJECTS, numberFailed);
            if (result==0){
                Logger.setReport(NO_DIFFERENCE_OBJECT_STATUS);
                Logger.setReport(MANUAL_ANALYS_NECESSARY);
            }
        }
    }//compareConversionReports

    public void compareErrorReports (ArrayList<String> report, ArrayList<String> reference, int numberFailed){
        int result;
        //Get statistic by categories
        Logger.setReport(MARKER_2+BY_CATEGORIES);
        result = compareStatisticBySource(report,reference,STATISTIC_BY_CATEGORIES);
        if (result==0){
            Logger.setReport(NO_CHANGES);
        }
        else {
            result=compareStatisticBySource(report,reference,GET_OBJECTS, numberFailed);
            if (result==0){
                Logger.setReport(NO_DIFFERENCE_OBJECT_STATUS);
                Logger.setReport(MANUAL_ANALYS_NECESSARY);
            }
        }
    }//compareErrorReports

    public void compareAIReports (ArrayList<String> report, ArrayList<String> reference, int numberFailed){
        int result;
//Get general statistic;
        Logger.setReport(MARKER_2+GENERAL_STATISTIC);
        result = compareStatisticBySource(report,reference,GENERAL_STATISTIC);
        if (result==0){
            Logger.setReport(NO_CHANGES);
        }
        else {
            result=compareStatisticBySource(report,reference,GET_OBJECTS, numberFailed);
            if (result==0){
                Logger.setReport(NO_DIFFERENCE_OBJECT_STATUS);
                Logger.setReport(MANUAL_ANALYS_NECESSARY);
            }
        }
    }//compareAIReports

    public void csvCompare(File reportFile, File referenceFile, int numberFailed){


        ArrayList<String> report = new ArrayList<String>(ReportReader.readFile(reportFile));
        ArrayList<String> reference = new ArrayList<String>(ReportReader.readFile(referenceFile));

        Logger.setReport(MARKER+reportFile.getName());

        if (report.stream().anyMatch((p)->p.trim().equalsIgnoreCase(APPLY))){
             compareApplyReports(report,reference, numberFailed);
        }

        else if (report.stream().anyMatch((p)->p.trim().equalsIgnoreCase(CONVERSION))){
            compareConversionReports(report,reference,numberFailed);
        }

        else if (report.stream().anyMatch((p)->p.trim().equalsIgnoreCase(AI) || p.equalsIgnoreCase(ACTION_ITEMS))){
           compareErrorReports(report,reference,numberFailed);
        }

        else if (report.stream().anyMatch((p)->p.trim().equalsIgnoreCase(ERROR))){
            compareAIReports(report,reference, numberFailed);
        }
        else {
            Logger.setReport(COULD_NOT_IDENTIFY_REPORT_TYPE);
        }
    }//csvCompare

}
