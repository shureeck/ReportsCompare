import java.io.File;
import java.util.ArrayList;

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
}
