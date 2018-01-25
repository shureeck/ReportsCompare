import java.util.Scanner;

import static stringconstant.LoggerMessages.*;
import static stringconstant.StringsConstants.*;

public class Input {
    public static String input(String outputString){
        String inputString=null;
                System.out.println(outputString);
        Scanner scanner=null;
        try{
            scanner = new Scanner(System.in);
            inputString=scanner.nextLine().trim();
            inputString=inputString.replaceAll( UNVISIBLE_u202A, EMPTY);
            Logger.setLog(LINE_READ_SUCCESSFULLY);

             if(inputString.equalsIgnoreCase(EMPTY) || inputString==null){
                 Logger.setLog(STRING_EMPTY);

                 inputString=null;
             }
        }
        catch (Exception e){
           Logger.setLog(COULD_NOT_READ_LINE + "\n"+e.getMessage());
           //Logger.setLog(e.getStackTrace().toString());
        }
       /* finally {
            scanner.close();
        }*/
        return inputString;
    }

    public static int inputNumber(String outputString){
        String inputString=null;
        Scanner scanner=null;
        int numberFailed=5;
        try{
            while(true) {
                System.out.println(outputString);
                scanner = new Scanner(System.in);
                inputString = scanner.nextLine().trim();
                inputString = inputString.replaceAll(UNVISIBLE_u202A, EMPTY);

                if (inputString.trim().matches(ONLY_NUMBERS) || inputString.trim().equals(EMPTY))
                {
                    Logger.setLog(LINE_READ_SUCCESSFULLY);
                    break;
                }

                else{
                        System.out.println(NO_NUMBERS);
                        System.out.println(MUST_INPUT_NUMBERS);
                }

            }

            if(inputString.matches(ONLY_NUMBERS)){
                numberFailed=Integer.parseInt(inputString.trim());
            }
        }
        catch (Exception e){
            Logger.setLog(COULD_NOT_READ_LINE + "\n"+e.getMessage());
            //Logger.setLog(e.getStackTrace().toString());
        }
       /* finally {
            scanner.close();
        }*/
        return numberFailed;
    }
}
