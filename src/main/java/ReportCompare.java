import java.io.File;
import java.util.ArrayList;

import static stringconstant.ReportMessages.*;
import static stringconstant.StringsConstants.*;

/**
 * Created by Poliakov.A on 1/24/2018.
 */
public class ReportCompare {
    public void reportCompare(){
        ReportCopy.createFileCopy(new File(REPORT_CSV));

        //Input data
        String referencePath = Input.input(PREVIOUS_REPORTS_PATH);
        String reportsPath = Input.input(CURRENT_REPORTS_PATH);
        int numberFailed = Input.inputNumber(NUMBER_FEILED_OBJECTS);

        ArrayList <File> reportsList = new ArrayList<>(ReportsList.getReportsList(new File(reportsPath)));
        ArrayList <File> referenceList = new ArrayList<>(ReportsList.getReportsList(new File(referencePath)));

        //Get build numbers
        int i =0;
        String buildNumber=null;
        while(i<referenceList.size()) {
            buildNumber = ReportParser.getBuildNumber(referenceList.get(i));
            if (buildNumber!=null) break;
            i++;
        }
        Logger.setReport(REFERENCE_BUILD+buildNumber);

        while(i<reportsList.size()) {
            buildNumber = ReportParser.getBuildNumber(reportsList.get(i));
            if (buildNumber!=null) break;
            i++;
        }
        Logger.setReport(CURRENT_BUILD+buildNumber+"\n");

        //Report compare
        Report compareReport = new Report();
        CSV_Compare csv_compare = new CSV_Compare();

        while(reportsList.size()!=0) {
            ArrayList<File> reportsByPair = ReportsList.getReportsForPair(reportsList);//getReportsForPair get always item[0]

            Logger.setReport(reportsByPair.get(0).getParentFile().getPath());

            i = 0;
            while (i < reportsByPair.size()) {
                //Get common part of path for reference ane report
                String path = reportsByPair.get(i).getPath().substring(reportsPath.length());

                //Get report and reference files
                File referenceFile = referenceList.stream().filter((p) -> p.getPath().endsWith(path)).findFirst().orElse(null);
                File reportFile = reportsByPair.get(i);

                if (referenceFile!=null && reportFile!=null) {
                    compareReport.addALL(csv_compare.csvCompare(reportFile, referenceFile, numberFailed));
                    referenceList.removeIf((p) -> p.getPath().endsWith(path));
                }
                else {
                    Logger.setReport(FILE_WAS_NOT_FOUND+path);
                }
                i++;
            }
            compareReport.getApply().stream().forEach((p)->Logger.setReport(p));
            compareReport.getConvert().stream().forEach((p)->Logger.setReport(p));
            compareReport.getError().stream().forEach((p)->Logger.setReport(p));
            compareReport.getAI().stream().forEach((p)->Logger.setReport(p));

            Logger.setReport(SEPARATOR);
            compareReport.clearALL();
        }//while

    }

}
