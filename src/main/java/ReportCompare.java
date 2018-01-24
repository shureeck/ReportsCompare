import java.io.File;
import java.util.ArrayList;

import static stringconstant.ReportMessages.*;

/**
 * Created by Poliakov.A on 1/24/2018.
 */
public class ReportCompare {
    public void reportCompare(){


        String reportsPath="e:\\AutoTests\\Reports\\Main2206\\reports\\";
        String referencePath="e:\\AutoTests\\Reports\\Main2197\\reports\\";

        ArrayList <File> reportsList = new ArrayList<>(ReportsList.getReportsList(new File(reportsPath)));
        ArrayList <File> referenceList = new ArrayList<>(ReportsList.getReportsList(new File(referencePath)));

        //Get build numbers
        int i =0;
        String buildNumber=null;
        while(i<referenceList.size()) {
            buildNumber = ReportParser.getBuildNumber(referenceList.get(i));
            if (buildNumber!=null) break;
        }
        Logger.setReport(REFERENCE_BUILD+buildNumber+"\n");

        while(i<reportsList.size()) {
            buildNumber = ReportParser.getBuildNumber(reportsList.get(i));
            if (buildNumber!=null) break;
        }
        Logger.setReport(CURRENT_BUILD+buildNumber+"\n\n");

        //Report compare
        CSV_Compare csv_compare = new CSV_Compare();

        while(reportsList.size()!=0) {
            ArrayList<File> reportsByPair = ReportsList.getReportsForPair(reportsList);//getReportsForPair get always item[0]

            Logger.setReport(reportsByPair.get(0).getParentFile().getPath()+"\n");

            i = 0;
            while (i < reportsByPair.size()) {
                //Get common part of path for reference ane report
                String path = reportsByPair.get(i).getPath().substring(referencePath.length());

                //Get report and reference files
                File referenceFile = referenceList.stream().filter((p) -> p.getPath().endsWith(path)).findFirst().orElse(null);
                File reportFile = reportsByPair.get(i);

                if (referenceFile!=null &&reportFile!=null) {
                    csv_compare.csvCompare(reportFile, referenceFile);
                    referenceList.removeIf((p) -> p.getPath().endsWith(path));
                }
                else {
                    Logger.setReport(FILE_WAS_NOT_FOUND+path+"\n");
                }
                i++;
            }
            Logger.setReport(SEPARATOR+"\n");
        }//while

    }

}
