import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static stringconstant.LoggerMessages.*;
import static stringconstant.StringsConstants.*


;

/**
 * Created by Poliakov.A on 1/17/2018.
 */
public class ReportCopy {
    public static void createFileCopy(File sourse){
        File dir =new File(TEMP);
        if (sourse.exists()) {
            if (!dir.exists()) {
                dir.mkdir();
            }

            Date date = new Date();
            SimpleDateFormat formatDate = new SimpleDateFormat(DATE_FORMAT_REPORT);
            SimpleDateFormat formatTime = new SimpleDateFormat(TIME_FORMAT_REPORT);

            File target = new File(dir.toPath() + "\\" +  formatDate.format(date)+"_"+ formatTime.format(date)+"_"+ sourse.getName());
            try {
                Files.copy(sourse.toPath(), target.toPath(), REPLACE_EXISTING);
                Logger.setLog(REPORT_FILE_WAS_COPIED_INTO + target.getAbsolutePath());

                Files.deleteIfExists(sourse.toPath());

                Logger.setLog(REPORT_FILE_WAS_DELETED);
            } catch (IOException e) {
                Logger.setLog(IMPOSSIBLE_COPY_FILE + sourse.getName() + "\n" + e.getMessage());
                // Logger.setLog(e.getStackTrace().toString());
                e.printStackTrace();
            }
        }
    }
}
