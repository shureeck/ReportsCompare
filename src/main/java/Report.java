import java.util.ArrayList;

import static stringconstant.StringsConstants.*;

public class Report {
    private ArrayList<String>Apply=new ArrayList<>();
    private ArrayList<String>Convert=new ArrayList<>();
    private ArrayList<String>Error =new ArrayList<>();
    private ArrayList<String>AI=new ArrayList<>();

    public void setApplyReport(String str){
        Apply.add(str);
    }

    public void setAllApplyReport(ArrayList<String> str){
        Apply.addAll(str);
    }

    public void setConvertReport(String str){
       Convert.add(str);
    }
    public void setAllConvertReport(ArrayList<String> str){
        Convert.addAll(str);
    }

    public void setErrorReport(String str){
       Error.add(str);
    }

    public void setAllErrorReport(ArrayList<String> str){
        Error.addAll(str);
    }

    public void setAIReport(String str){
        AI.add(str);
    }

    public void setAllAIReport(ArrayList<String> str){
        AI.addAll(str);
    }

    public void addALL(ArrayList<String>report){
        if (report.stream().anyMatch((p)->p.contains(STATISTIC_BY_SOURCE_XML))){
            setAllApplyReport(report);
        }
        else if (report.stream().anyMatch((p)->p.contains(GENERAL_STATISTIC))&&report.stream().anyMatch((p)->p.contains(BY_CATEGORIES))){
            setAllConvertReport(report);
        }
        else if(report.stream().anyMatch((p)->p.contains(BY_CATEGORIES))){
            setAllErrorReport(report);
        }
        else if (report.stream().anyMatch((p)->p.contains(GENERAL_STATISTIC))){
            setAllAIReport(report);
        }
    }

    public ArrayList<String> getApply() {
        return Apply;
    }

    public ArrayList<String> getConvert() {
        return Convert;
    }

    public ArrayList<String> getError() {
        return Error;
    }

    public ArrayList<String> getAI(){
        return AI;
    }
}
