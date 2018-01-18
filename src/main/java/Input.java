import java.util.Scanner;

import static stringconstant.LoggerMessages.*;
import static stringconstant.StringsConstants.*;

public class Input {
    public String input(String outputString){
        String inputString=null;
                System.out.println(outputString);
        Scanner scanner=null;
        try{
            scanner = new Scanner(System.in);
            inputString=scanner.nextLine().trim();
            inputString=inputString.replaceAll( UNVISIBLE_u202A, EMPTY);
            System.out.println(LINE_READ_SUCCESSFULLY);
            Logger.setLog(LINE_READ_SUCCESSFULLY);

             if(inputString.equalsIgnoreCase(EMPTY) || inputString==null){
                 System.out.println(STRING_EMPTY);
                 Logger.setLog(STRING_EMPTY);

                 inputString=null;
             }
        }
        catch (Exception e){
           System.out.println(COULD_NOT_READ_LINE);
           Logger.setLog(COULD_NOT_READ_LINE + "\n"+e.getMessage());
           //Logger.setLog(e.getStackTrace().toString());
        }
       /* finally {
            scanner.close();
        }*/
        return inputString;
    }
}
