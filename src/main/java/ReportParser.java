import java.util.ArrayList;

import static stringconstant.StringsConstants.*;

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


    public static String getBuildNumber(ArrayList<String> report){
        String buildNumber=null;

        buildNumber = report.stream().filter((p)->p.matches(BUILD_NUMBER)).findFirst().get().toString();
        buildNumber=buildNumber.substring(buildNumber.indexOf(COMA)+1).trim();

        return buildNumber;
    }
}//getBuildNumber
