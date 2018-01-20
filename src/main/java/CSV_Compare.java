import java.io.File;
import java.util.ArrayList;

import static stringconstant.StringsConstants.*;

/**
 * Created by Poliakov.A on 1/19/2018.
 */
public class CSV_Compare {

    public void compareStatisticBySource(ArrayList<String> report, ArrayList<String>reference){
        //Get collections with statistic y source
        ArrayList<String>tempReport = new ArrayList<>(ReportParser.getStatisticBySource(report));
        ArrayList<String>tempReference = new ArrayList<>(ReportParser.getStatisticBySource(reference));

        //Compare statistic
        compareReport(tempReport,tempReference);
    }// compareStatisticBySource

    public void compareReport(ArrayList<String> report, ArrayList<String>reference){
        int i=0;
        int count =0;
        while (report.size()>i){
            if (reference.stream().noneMatch((p)->p.equalsIgnoreCase(report.get(0))))
            {
               //will be changed to report or buffer
                Logger.setReport(reference.get(i));
                count++;
            }
            i++;

        }
    }//compareReport
}
