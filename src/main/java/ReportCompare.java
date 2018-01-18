import java.io.File;
import java.util.ArrayList;

/**
 * Created by Poliakov.A on 1/18/2018.
 */
public class ReportCompare {
    public static void main(String[] args){
        ArrayList <File> test = new ArrayList<File>(ReportsList.getReportsList(new File("e:\\AutoTests\\Temp\\ReportCompare\\")));

        ArrayList<String> report = new ArrayList<String>(ReportReader.Readfile(new File("E:\\AutoTests\\Temp\\ReportCompare\\Vertica_Redshift_apply.csv")));
        int i=0;


    }
}
