import java.util.ArrayList;

import static stringconstant.StringsConstants.*;

/**
 * Created by Poliakov.A on 1/19/2018.
 */
public class ReportParser {
    public static ArrayList<String> getStatisticBySource(ArrayList<String> report){
        ArrayList<String> statisticBySource = new ArrayList<>(null);

        int i=0;
        int swch=0;

        while(true){
            if (swch==1 && !report.get(i).trim().equalsIgnoreCase(EMPTY)){
                statisticBySource.add(report.get(i));
            }
            else if (swch==1 && report.get(i).trim().equalsIgnoreCase(EMPTY)){
                break;
            }

            if (report.get(i).equalsIgnoreCase(STATISTIC_BY_SOURCE_XML)){
                swch =1;
                i++;
            }
            i++;
        }

        return statisticBySource;
    }
}
