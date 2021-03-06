package stringconstant;

public class LoggerMessages {
   public static final String DONE = "Done: ";
   public static final String ERROR = "Error: ";
   public static final String WARNING = "Warning: ";

   public static final String FILE_WAS_FOUND = DONE + "File was found: \n";
   public static final String FILE_WAS_IGNORED = WARNING + "File was ignored \n";
   public static final String FILE_NOT_FOUND = ERROR + "File was not found ";
   public static final String IO_ERROR_GET_BUILD_NAMBER = ERROR + "Input/Output error during getting build number ";
   public static final String IO_ERROR = ERROR +"Input/Output error ";
   public static final String FILE_WAS_SUCCESSFULLY_READ = DONE +"File was successfully read \n";
   public static final String COULD_NOT_READ_LINE = ERROR + "Could not read line";
   public static final String STRING_EMPTY = ERROR + "String is emprty!";
   public static final String LINE_READ_SUCCESSFULLY = DONE + "Line was successfully read";
   public static final String NO_NUMBERS = "The inputted data is not a number!";
   public static final String MUST_INPUT_NUMBERS = "You must input a number or do not enter anything.";
   public static final String REPORT_FILE_WAS_COPIED_INTO = DONE + "Old report file was copied into: ";
   public static final String REPORT_FILE_WAS_DELETED = DONE + "Old report file was deleted";
   public static final String IMPOSSIBLE_COPY_FILE = ERROR + "Impossible coppy report file into: ";
}
