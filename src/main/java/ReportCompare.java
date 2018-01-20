import java.io.File;
import java.util.ArrayList;

/**
 * Created by Poliakov.A on 1/18/2018.
 */
public class ReportCompare {
    public static void main(String[] args){
      //  ArrayList <File> test = new ArrayList<File>(ReportsList.getReportsList(new File("e:\\AutoTests\\Temp\\ReportCompare\\")));
//"E:\\AutoTests\\Temp\\ReportCompare\\Vertica_Redshift_apply.csv"
        ArrayList<String> report = new ArrayList<String>(ReportReader.readFile(new File("E:\\Reports\\Oracle-PostgreSQL\\Oracle_WriteToDB.csv")));
        String BuildNumber = ReportParser.getBuildNumber(report).trim();

        CSV_Compare comoarator = new CSV_Compare();


    }
}
