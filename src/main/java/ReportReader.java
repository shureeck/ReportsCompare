import java.io.*;
import java.util.ArrayList;

import static stringconstant.LoggerMessages.*;

/**
 * Created by Poliakov.A on 1/18/2018.
 */
public class ReportReader {
    public static ArrayList<String> readFile(File file){
        ArrayList<String> report = new ArrayList<String>();

        FileReader scaner=null;
        BufferedReader buffer = null;


        try {
            scaner = new FileReader(file.getPath());
            buffer = new BufferedReader(scaner);
            String line=buffer.readLine().trim();

            while(line!=null){
                report.add(line);
                line=buffer.readLine();

            }
        }
        catch (FileNotFoundException e){
            Logger.setLog(FILE_NOT_FOUND + e.getMessage());
            e.printStackTrace();
        }
        catch (IOException e){
            Logger.setLog(IO_ERROR+e.getMessage());
            e.printStackTrace();
        }
        finally {
            try {
                scaner.close();
                buffer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (report!=null && report.size()>10) {
            Logger.setLog(FILE_WAS_SUCCESSFULLY_READ + file.getPath());
        }

        return report;
    }
}
