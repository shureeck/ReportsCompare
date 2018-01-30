import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static stringconstant.StringsConstants.*;
import static stringconstant.LoggerMessages.*;

/**
 * Created by Poliakov.A on 1/18/2018.
 */
public class ReportsList {
    public static  ArrayList<File> getReportsList (File reports){

        ArrayList<File> csvFilesList = new ArrayList<File>();

        File[] listReports = reports.listFiles();

        int i=0;
        while (listReports.length>i){
            if (listReports[i].isFile()){
                if(listReports[i].getName().endsWith(CSV)){
                    csvFilesList.add(listReports[i]);

                    Logger.setLog(FILE_WAS_FOUND+listReports[i].getPath());
                }
                else Logger.setLog(FILE_WAS_IGNORED+listReports[i].getPath());
            }
            else {
                csvFilesList.addAll(getReportsList(listReports[i]));
            }
            i++;
        }
        return csvFilesList;
    }

    public static ArrayList<File> getReportsForPair(ArrayList<File>reportsList){
        String parentReport = reportsList.get(0).getParentFile().getName();

        ArrayList<File>  reportsByPair = new ArrayList<>();
        reportsByPair.addAll(reportsList.stream().filter((p)->p.getParentFile().getName().equalsIgnoreCase(parentReport)).collect(Collectors.toCollection(ArrayList<File>::new)));

        int i =0;
        while(reportsByPair.size()>i){
            String path = reportsByPair.get(i).getPath();
            reportsList.removeIf((p)->p.getPath().equalsIgnoreCase(path));
            i++;
        }
        return reportsByPair;
    }
}
