package MathFunc;

//import Others.*;

import Others.Others;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**Class describing a parameter
 * Created by jwt625 on 10/28/2015.
 *
 * @author jwt625
 */
public class Para extends MathObject {

    /**
     * chosen value of the parameter
     */
    String ParaValue;

    /**
     * whether the parameter is used or not
     */
    boolean ShowName;

    /**
     * nummber of values
     */
    int NumOfValues;

    /**
     * all parameter values
     */
    String[] AllParaValue;

    /**
     * default method to generate an empty parameters
     */
    public Para() {
        super();
        ParaValue = "default";
        ShowName = false;
        NumOfValues = 0;
        AllParaValue = new String[20];
        for (int i = 0; i < 20; i++) {
            AllParaValue[i] = "";
        }
    }

    /**set parameter is used or not
     * @param showName true if used, else false
     */
    public void setShowName(boolean showName) {
        ShowName = showName;
    }

    /**set parameter value
     * @param NewValue value to set
     */
    public void setParaValue(String NewValue) {
        ParaValue = NewValue;
    }

    public void setAllParaValue(String[] NewValues) {
        NumOfValues = NewValues.length;
        for (int i = 0; i < NumOfValues; i++) {
            AllParaValue[i] = NewValues[i];
        }
    }

    /**add a value
     * @param NewValue value to add
     */
    public void addParaValue(String NewValue) {
        AllParaValue[NumOfValues] = NewValue;
        NumOfValues++;
    }

    public int getNumOfValues() {
        return NumOfValues;
    }

    public String getParaValue() {
        return ParaValue;
    }

    /**get all parametric values
     * @return an array of String of all parameter values
     */
    public String[] getAllParaValue() {
        return AllParaValue;
    }

    public boolean isShowName() {
        return ShowName;
    }

    /**
     * print the information of this parameter
     * for testing
     */
    public void print() {
        System.out.print("\t\t" + ObjectName + " " + UseFreq + "\n");
        System.out.print("\t\t\tPara notes: ");
        System.out.println(Notes);
    }

    /**load all information of this parameter from file
     * @param upperPath path of the file of the upper MathFunc
     * @param paraName name of this object
     */
    public void loadAll(String upperPath, String paraName) {
        loadPara(upperPath, paraName);
        loadNotes(upperPath, paraName);
    }

    /**load paraeter from file
     * @param upperpath path of the file of the upper MathFunc
     * @param paraname name of this object
     */
    public void loadPara(String upperpath, String paraname) {

        //System.out.println(paraname);

        ObjectName = paraname;
        UpperPath = upperpath;
        String FilePath = upperpath + "\\" + paraname + "Values.txt";
        File file = new File(FilePath);
        File dir = new File(file.getParent());
        try {
            if (!dir.isDirectory()) {
                dir.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e0) {
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

    /**load notes from file
     * @param upperpath path of the file of the upper MathFunc
     * @param paraname name of this object
     */
    public void loadNotes(String upperpath, String paraname) {

        //System.out.println(paraname);

        String FilePath = upperpath + "\\" + paraname + "notes.txt";
        File file = new File(FilePath);
        File dir = new File(file.getParent());
        try {
            if (!dir.isDirectory()) {
                dir.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e0) {
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
                Notes = Notes + notes + "\n";
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

    public String getPathOfValues() {
        return UpperPath + "\\" + ObjectName + "Values.txt";
    }

    public String getPathOfNotes() {
        return UpperPath + "\\" + ObjectName + "notes.txt";
    }

    /**
     * output information to file
     */
    public void outputFile() {
        Others otherFuncs = new Others();
        String PathOfValues = UpperPath + "\\" + ObjectName + "Values.txt";
        for (int i = 0; i < NumOfValues; i++) {
            if (i == 0) {
                otherFuncs.writeFile(PathOfValues, AllParaValue[i], false);
            } else {
                otherFuncs.writeFile(PathOfValues, "\r\n" + AllParaValue[i], true);
            }
        }

        String PathOfNotes = UpperPath + "\\" + ObjectName + "notes.txt";
        otherFuncs.writeFile(PathOfNotes, Notes, false);
    }

    /**
     * @return command part of this parameter
     */
    public String show() {
        if (ShowName) {
            return ", " + ObjectName + "->" + ParaValue;
        }
        return "";
    }
}
