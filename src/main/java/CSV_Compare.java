import java.util.ArrayList;

/**
 * Created by Poliakov.A on 1/19/2018.
 */
public class CSV_Compare {

    public int compareStatisticBySource(ArrayList<String> report, ArrayList<String>reference, String statisticType){
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
                Logger.setReport(reference.get(i));
                count++;
            }
            i++;

        }
        return count;
    }//compareReport
}
