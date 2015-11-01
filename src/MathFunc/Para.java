package MathFunc;

//import Others.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by IIIS on 10/28/2015.
 */
public class Para extends MathObject {

    String ParaValue;
    boolean ShowName;
    int NumOfValues;
    String[] AllParaValue;

    public Para(){
        super();
        ParaValue="";
        ShowName=false;
        NumOfValues=0;
        AllParaValue=new String[20];
        for(int i = 0; i < 20; i++){
            AllParaValue[i]="";
        }
    }

    public void setParaValue(String NewValue){
        ParaValue = NewValue;
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

    public int getNumOfValues(){return NumOfValues;}
    public String getParaValue(){return ParaValue;}
    public String[] getAllParaValue(){return AllParaValue;}
    public boolean isShowName(){return ShowName;}
    public void print(){
        System.out.print("\t\t" + ObjectName +" "+ UseFreq + "\n");
        System.out.print("\t\t\tPara notes: ");
        System.out.println(Notes);
    }

    public void loadAll(String upperPath, String paraName){
        loadPara(upperPath, paraName);
        loadNotes(upperPath, paraName);
    }
    public void loadPara(String upperpath, String paraname) {

        //System.out.println(paraname);

        ObjectName=paraname;
        UpperPath=upperpath;
        String FilePath = upperpath+"\\"+paraname+".txt";
        File file = new File(FilePath);
        File dir = new File(file.getParent());
        try{
            if (!dir.isDirectory()) {
                dir.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        }catch (IOException e0) {
            e0.printStackTrace();
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String paravalue = null;
            /*String[] allparavalue=new String[100];
            for(int i = 0; i < 100; i++){
                allparavalue[i]="";
            }*/
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
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
        File dir = new File(file.getParent());
        try{
            if (!dir.isDirectory()) {
                dir.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        }catch (IOException e0) {
            e0.printStackTrace();
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String notes = null;
            /*String[] allparavalue=new String[100];
            for(int i = 0; i < 100; i++){
                allparavalue[i]="";
            }*/
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
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
