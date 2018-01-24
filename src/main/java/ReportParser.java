import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static stringconstant.StringsConstants.*;
import static stringconstant.LoggerMessages.*;

/**
 * Created by Poliakov.A on 1/19/2018.
 */
public class ReportParser {
    public static ArrayList<String> getStatistic(ArrayList<String> report, String statisticType){
        ArrayList<String> statistic = new ArrayList<>();

        int i=0;
        int swch=0;

        while(report.size()>i){
            if (swch==1 && !report.get(i).trim().equalsIgnoreCase(EMPTY)){
                statistic.add(report.get(i));
            }
            else if (swch==1 && report.get(i).trim().equalsIgnoreCase(EMPTY)){
                break;
            }

            if (report.get(i).trim().equalsIgnoreCase(statisticType)){
                swch =1;
            }
            i++;
        }
        return statistic;
    }// getStatistic


    public static String getBuildNumber(File report){
        String buildNumber=null;

        try {
            buildNumber = Files.lines(Paths.get(report.toURI())).filter((p)->p.matches(BUILD_NUMBER)).findFirst().orElse(null);
        } catch (FileNotFoundException e){
            Logger.setLog(FILE_NOT_FOUND+e.getMessage());

        } catch (IOException e) {
            Logger.setLog(IO_ERROR_GET_BUILD_NAMBER);
            e.printStackTrace();
        }
        if (buildNumber!=null) {
            buildNumber = buildNumber.substring(buildNumber.indexOf(COMA) + 1).trim();
        }
        return buildNumber;
    }
}//getBuildNumber
