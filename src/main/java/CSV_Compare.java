import java.io.File;
import java.util.ArrayList;

import static stringconstant.StringsConstants.*;
import static stringconstant.ReportMessages.*;

/**
 * Created by Poliakov.A on 1/19/2018.
 */
public class CSV_Compare {

    ArrayList<String> tempArray = null;

    private ArrayList<String> compareStatisticBySource(ArrayList<String> report, ArrayList<String> reference, String statisticType) {
        //Get collections with statistic y source
        ArrayList<String> tempReport = new ArrayList<>(ReportParser.getStatistic(report, statisticType));
        ArrayList<String> tempReference = new ArrayList<>(ReportParser.getStatistic(reference, statisticType));
        //Compare statistic
        ArrayList<String> result = compareReport(tempReport, tempReference);

        return result;
    }// compareStatisticBySource

    private String getItemFromReport(String item, ArrayList<String> report) {
        String result = report.stream().filter((p) -> (p.contains(item.split(COMA)[0]))).findFirst().orElse("null");
        return result;
    }

    private String getObjectFromReport(String object, ArrayList<String> report) {
        String[] splitedobject = object.split(COMA);
        String result = "null";

        if (splitedobject.length > 3) {
            String objectTypeName = splitedobject[1] + COMA + splitedobject[2];
            result = report.stream().filter((p) -> (p.contains(objectTypeName))).findFirst().orElse("null");
        }
        if (result.length() > 130) return result.substring(0, 130) + THREE_DOTS;
        else return result;
    }

    private ArrayList<String> compareReport(ArrayList<String> report, ArrayList<String> reference) {
        ArrayList<String> failedString = new ArrayList<>();
        failedString.add(COMA + PREVIOUS_CURRENT);
        int i = 0;
        while (report.size() > i) {
            String temp = report.get(i);
            if (reference.stream().noneMatch((p) -> p.equalsIgnoreCase(temp))) {
                String referenceValue = getItemFromReport(temp, reference);
                failedString.add(COMA + "\"" + referenceValue + "\"" + COMA + "\"" + report.get(i) + "\"" + COMA);
            }
            i++;
        }
        if (failedString.size() == 1) failedString.clear();
        return failedString;
    }//compareReport

    private ArrayList<String> compareStatisticBySource(ArrayList<String> report, ArrayList<String> reference, String statisticType, int numberFailedObjects) {
        //Get collections with statistic y source
        ArrayList<String> tempReport = new ArrayList<>(ReportParser.getStatistic(report, statisticType));
        ArrayList<String> tempReference = new ArrayList<>(ReportParser.getStatistic(reference, statisticType));
        //Compare statistic
        ArrayList<String> result = compareReport(tempReport, tempReference, numberFailedObjects);

        return result;
    }// compareStatisticBySource

    private ArrayList<String> compareReport(ArrayList<String> report, ArrayList<String> reference, int numberFailedObjects) {
        ArrayList<String> failedString = new ArrayList<>();
        failedString.add(COMA + PREVIOUS_CURRENT);
        int i = 0;
        int count = 0;
        while (report.size() > i) {
            String temp = report.get(i);
            if (reference.stream().noneMatch((p) -> p.equalsIgnoreCase(temp))) {
                if (count < numberFailedObjects || numberFailedObjects == 0) {

                    String previousResult = getObjectFromReport(report.get(i), reference);

                    if (report.get(i).length() > 130)
                        failedString.add(COMA + "\"" + previousResult + "\"" + COMA + "\"" +
                                report.get(i).substring(0, 130) + THREE_DOTS + "\"" + COMA);
                    else
                        failedString.add(COMA + "\"" + previousResult + "\"" + COMA + "\"" + report.get(i) + "\"" + COMA);
                }
                count++;
            }
            i++;
        }
        if (failedString.size() == 1) failedString.clear();
        failedString.add("\n" + SEVERAL_TAB + count + OBJECTS_ARE_DIFFER);
        return failedString;
    }//compareReport

    public ArrayList<String> compareApplyReports(ArrayList<String> report, ArrayList<String> reference, int numberFailed) {
        ArrayList<String> reportStrings = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();

        result.addAll(compareStatisticBySource(report, reference, STATISTIC_BY_SOURCE_XML));
        if (result.size() == 0) {
            reportStrings.add(MARKER_2 + STATISTIC_BY_SOURCE_XML + NO_CHANGES);
        } else {
            reportStrings.add(MARKER_2 + STATISTIC_BY_SOURCE_XML);
            reportStrings.addAll(result);
        }

        result.clear();
        result.addAll(compareStatisticBySource(report, reference, GENERAL_STATISTIC));
        if (result.size() == 0) {
            reportStrings.add(MARKER_2 + GENERAL_STATISTIC + NO_CHANGES);
        } else {
            reportStrings.add(MARKER_2 + GENERAL_STATISTIC);
            reportStrings.addAll(result);
        }

        result.clear();
        result.addAll(compareStatisticBySource(report, reference, STATISTIC_BY_CATEGORIES));
        if (result.size() == 0) {
            reportStrings.add(MARKER_2 + BY_CATEGORIES + NO_CHANGES);
        } else {
            reportStrings.add(MARKER_2 + BY_CATEGORIES);
            reportStrings.addAll(result);

            result.clear();
            result.addAll(compareStatisticBySource(report, reference, GET_OBJECTS, numberFailed));
            reportStrings.addAll(result);

        }
        return reportStrings;
    }//compareApplyReports

    public ArrayList<String> compareConversionReports(ArrayList<String> report, ArrayList<String> reference, int numberFailed) {
        ArrayList<String> reportStrings = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();

        result.clear();
        result.addAll(compareStatisticBySource(report, reference, GENERAL_STATISTIC));
        if (result.size() == 0) {
            reportStrings.add(MARKER_2 + GENERAL_STATISTIC + NO_CHANGES);
        } else {
            reportStrings.add(MARKER_2 + GENERAL_STATISTIC);
            reportStrings.addAll(result);
        }

        result.clear();
        result.addAll(compareStatisticBySource(report, reference, STATISTIC_BY_CATEGORIES));
        if (result.size() == 0) {
            reportStrings.add(MARKER_2 + BY_CATEGORIES + NO_CHANGES);
        } else {
            reportStrings.add(MARKER_2 + BY_CATEGORIES);
            reportStrings.addAll(result);

            result.clear();
            result.addAll(compareStatisticBySource(report, reference, GET_OBJECTS, numberFailed));
            if (result.size() == 0) {
                reportStrings.add(NO_DIFFERENCE_OBJECT_STATUS);
                reportStrings.add(MANUAL_ANALYS_NECESSARY);
            } else {
                reportStrings.addAll(result);
            }
        }
        return reportStrings;
    }//compareConversionReports

    public ArrayList<String> compareErrorReports(ArrayList<String> report, ArrayList<String> reference, int numberFailed) {
        ArrayList<String> reportStrings = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();

        result.clear();
        result.addAll(compareStatisticBySource(report, reference, STATISTIC_BY_CATEGORIES));
        if (result.size() == 0) {
            reportStrings.add(MARKER_2 + BY_CATEGORIES + NO_CHANGES);
        } else {
            reportStrings.add(MARKER_2 + BY_CATEGORIES);
            reportStrings.addAll(result);

            result.clear();
            result.addAll(compareStatisticBySource(report, reference, GET_OBJECTS, numberFailed));
            if (result.size() == 0) {
                reportStrings.add(NO_DIFFERENCE_OBJECT_STATUS);
                reportStrings.add(MANUAL_ANALYS_NECESSARY);
            } else {
                reportStrings.addAll(result);
            }
        }
        return reportStrings;
    }//compareErrorReports

    public ArrayList<String> compareAIReports(ArrayList<String> report, ArrayList<String> reference, int numberFailed) {
        ArrayList<String> reportStrings = new ArrayList<>();
        ArrayList<String> result = new ArrayList<>();
//Get general statistic;

        result.clear();
        result.addAll(compareStatisticBySource(report, reference, GENERAL_STATISTIC));
        if (result.size() == 0) {
            reportStrings.add(MARKER_2 + GENERAL_STATISTIC + NO_CHANGES);
        } else {
            reportStrings.add(MARKER_2 + GENERAL_STATISTIC);
            reportStrings.addAll(result);

            result.clear();
            result.addAll(compareStatisticBySource(report, reference, GET_OBJECTS, numberFailed));
            if (result.size() == 0) {
                reportStrings.add(NO_DIFFERENCE_OBJECT_STATUS);
                reportStrings.add(MANUAL_ANALYS_NECESSARY);
            } else {
                reportStrings.addAll(result);
            }
        }
        return reportStrings;
    }//compareAIReports

    public ArrayList<String> csvCompare(File reportFile, File referenceFile, int numberFailed) {

        ArrayList<String> reportByTypes = new ArrayList<>();


        ArrayList<String> report = new ArrayList<String>(ReportReader.readFile(reportFile));
        ArrayList<String> reference = new ArrayList<String>(ReportReader.readFile(referenceFile));

        if (report.stream().anyMatch((p) -> p.trim().equalsIgnoreCase(APPLY))) {
            reportByTypes.add(MARKER + reportFile.getName());
            reportByTypes.addAll(compareApplyReports(report, reference, numberFailed));
        } else if (report.stream().anyMatch((p) -> p.trim().equalsIgnoreCase(CONVERSION))) {
            reportByTypes.add(MARKER + reportFile.getName());
            reportByTypes.addAll(compareConversionReports(report, reference, numberFailed));
        } else if (report.stream().anyMatch((p) -> p.trim().equalsIgnoreCase(AI) || p.equalsIgnoreCase(ACTION_ITEMS))) {
            reportByTypes.add(MARKER + reportFile.getName());
            reportByTypes.addAll(compareErrorReports(report, reference, numberFailed));
        } else if (report.stream().anyMatch((p) -> p.trim().equalsIgnoreCase(ERROR))) {
            reportByTypes.add(MARKER + reportFile.getName());
            reportByTypes.addAll(compareAIReports(report, reference, numberFailed));
        } else {
            Logger.setReport(COULD_NOT_IDENTIFY_REPORT_TYPE);
        }
        return reportByTypes;
    }//csvCompare

}
