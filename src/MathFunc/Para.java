package MathFunc;

//import Others.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by IIIS on 10/28/2015.
 */
public class Para {
    String ParaName;
    String ParaValue;
    String UpperPath;
    String Notes;
    boolean ShowName;
    int UseFreq;
    int NumOfValues;
    String[] AllParaValue;

    public Para(){
        ParaName="";
        ParaValue="";
        UpperPath="";
        Notes="";
        ShowName=false;
        UseFreq=0;
        NumOfValues=0;
        AllParaValue=new String[20];
        for(int i = 0; i < 20; i++){
            AllParaValue[i]="";
        }
    }

    public void setNotes(String notes) {
        Notes = notes;
    }
    public void setUpperPath(String upperPath) {
        UpperPath = upperPath;
    }
    public void setParaName(String NewName){
        ParaName = NewName;
    }
    public void setParaValue(String NewValue){
        ParaValue = NewValue;
    }
    public void setUseFreq(int freq){
        UseFreq=freq;
    }
    public void setAllParaValue(String[] NewValues){
        int NumOfValues = NewValues.length;
        for(int i = 0; i < NumOfValues; i++){
            AllParaValue[i] = NewValues[i];
        }
    }
    public void addParaValue(String NewValue){
        AllParaValue[NumOfValues]=NewValue;
        NumOfValues++;
    }
    public void oneUse(){UseFreq++;}

    public String getNotes() {
        return Notes;
    }
    public String getUpperPath() {
        return UpperPath;
    }
    public int getUseFreq(){return UseFreq;}
    public int getNumOfValues(){return NumOfValues;}
    public String getParaName(){return ParaName;}
    public String getParaValue(){return ParaValue;}
    public String[] getAllParaValue(){return AllParaValue;}
    public boolean isShowName(){return ShowName;}
    public void print(){
        System.out.print("\t\t" + ParaName +" "+ UseFreq + "\n");
        System.out.print("\t\t\tPara notes: ");
        System.out.println(Notes);
    }

    public void loadAll(String upperPath, String paraName){
        loadPara(upperPath, paraName);
        loadNotes(upperPath, paraName);
    }
    public void loadPara(String upperpath, String paraname) {

        //System.out.println(paraname);

        ParaName=paraname;
        UpperPath=upperpath;
        String FilePath = upperpath+"\\"+paraname+".txt";
        File file = new File(FilePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String paravalue = null;
            /*String[] allparavalue=new String[100];
            for(int i = 0; i < 100; i++){
                allparavalue[i]="";
            }*/
            int line = 1;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((paravalue = reader.readLine()) != null) {
                this.addParaValue(paravalue);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
    public void loadNotes(String upperpath, String paraname) {

        //System.out.println(paraname);

        String FilePath = upperpath+"\\"+paraname+"notes.txt";
        File file = new File(FilePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String notes = null;
            /*String[] allparavalue=new String[100];
            for(int i = 0; i < 100; i++){
                allparavalue[i]="";
            }*/
            int line = 1;
            // һ�ζ���һ�У�ֱ������nullΪ�ļ�����
            while ((notes = reader.readLine()) != null) {
                Notes=notes;
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}
