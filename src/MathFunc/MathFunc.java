package MathFunc;

import Others.Others;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**Class describing MathFunc, with two kinds of parameters
 * Created by jwt625 on 10/28/2015.
 *
 * @author jwt625
 */
public class MathFunc extends MathObject {

    /**
     * All parameters
     */
    Para[] AllPara;

    /**
     * number of parameters
     */
    int NumOfPara;

    /**
     * main user input, a function to plot or data to manipulate
     */
    String MainField;

    /**
     * Note of the mainField
     */
    String MainFieldNote;

    /**
     * All VarRangePara
     */
    VarRangePara[] AllVarRangePara = new VarRangePara[10];

    /**
     * number of all VarRangePara
     */
    int NumOfVarRangePara;

    /**
     * default method to generate an empty MathFunc
     */
    public MathFunc() {
        super();
        NumOfPara = 0;
        NumOfVarRangePara = 0;
        //AllVarRangePara[0] = new VarRangePara();
        MainField = "";
    }

    /**method to generate a MathFunc with given upperPath and name
     * @param upperPath path of the file of the upper FuncClass
     * @param FuncName name of this Object
     */
    public MathFunc(String upperPath, String FuncName) {
        super();
        this.setUpperPath(upperPath);
        this.setObjectName(FuncName);
        NumOfPara = 0;
        NumOfVarRangePara = 0;
        //AllVarRangePara[0] = new VarRangePara();
        MainField = "";
    }

    public void setAllPara(Para[] NewParas) {
        NumOfPara = NewParas.length;
        for (int i = 0; i < NumOfPara; i++) {
            AllPara[i] = NewParas[i];
        }
    }

    public String getMainFieldNote() {
        return MainFieldNote;
    }


    public void setNumOfPara(int numOfPara) {
        NumOfPara = numOfPara;
    }

    public void setMainFieldNote(String mainFieldNote) {
        MainFieldNote = mainFieldNote;
    }

    public void setAllVarRangePara(VarRangePara[] allVarRangePara) {
        AllVarRangePara = allVarRangePara;
    }

    public void setNumOfVarRangePara(int numOfVarRangePara) {
        NumOfVarRangePara = numOfVarRangePara;
    }

    /**add a parameter
     * @param NewPara new parameter to add
     */
    public void addPara(Para NewPara) {
        Para[] tempPara = new Para[NumOfPara + 1];
        for (int i = 0; i < NumOfPara; i++) {
            tempPara[i] = AllPara[i];
        }
        AllPara = null;
        AllPara = tempPara;
        AllPara[NumOfPara] = NewPara;
        NumOfPara++;
    }

    /**add a VarRangePara
     * @param newVarRangePara new VarRangePara to add
     */
    public void addVarRangePara(VarRangePara newVarRangePara) {
        AllVarRangePara[NumOfVarRangePara] = newVarRangePara;
        NumOfVarRangePara++;
    }

    public void addPara(String ParaName) {
        Para[] tempPara = new Para[NumOfPara + 1];
        for (int i = 0; i < NumOfPara; i++) {
            tempPara[i] = AllPara[i];
        }
        AllPara = null;
        AllPara = tempPara;
        AllPara[NumOfPara] = new Para();
        AllPara[NumOfPara].setObjectName(ParaName);
        AllPara[NumOfPara].setUpperPath(UpperPath + "\\" + ObjectName);
        NumOfPara++;
    }

    /**
     * @return number of parameters
     */
    public int getNumOfPara() {
        return NumOfPara;
    }

    /**
     * @return number of VarRangePara
     */
    public int getNumOfVarRangePara() {
        return NumOfVarRangePara;
    }

    /**set the mainField
     * @param mainField mainField to set
     */
    public void setMainField(String mainField) {
        MainField = mainField;
    }

    /**
     * @return all parameters
     */
    public Para[] getAllPara() {
        return AllPara;
    }

    /**
     * @return all VarRangePara
     */
    public VarRangePara[] getAllVarRangePara() {
        return AllVarRangePara;
    }

    /**
     * print the information of this FuncClass, for testing
     */
    public void print() {
        System.out.print("\t" + ObjectName + " " + UseFreq);
        System.out.print("\tFunc notes: ");
        System.out.print(Notes + "\n\t\tParameters:\n");
        for (int i = 0; i < NumOfPara; i++) {
            AllPara[i].print();
        }
        for (int i = 0; i < NumOfVarRangePara; i++) {
            AllVarRangePara[i].print();
        }
        System.out.println("\t------end of Func " + ObjectName);
    }

    /**read all information from file
     * @param upperPath path of the file of the upper FuncClass
     * @param funcName name of this object
     */
    public void readAll(String upperPath, String funcName) {
        readPara(upperPath, funcName);
        readParaFreq(upperPath, funcName);
        readNotes(upperPath, funcName);
        readVarRangeParas(upperPath, funcName);
    }

    /**read all parameters from file
     * @param upperpath path of the file of the upper FuncClass
     * @param funcname name of this object
     */
    private void readPara(String upperpath, String funcname) {

        //System.out.println(funcname);

        ObjectName = funcname;
        UpperPath = upperpath;
        String FilePath = upperpath + "\\" + "F" + funcname + "\\" + funcname + "Para.txt";
        //File file = new File(FilePath);
        //BufferedReader reader = null;
        //try {
        //    reader = new BufferedReader(new FileReader(file));
        //    String paraname = null;
        //    int line = 1;
        //    // 一次读入一行，直到读入null为文件结束
        //    while ((paraname = reader.readLine()) != null) {
        //        NumOfPara++;
        //        line++;
        //    }
        //    reader.close();
        //} catch (IOException e) {
        //    e.printStackTrace();
        //} finally {
        //    if (reader != null) {
        //        try {
        //            reader.close();
        //        } catch (IOException e1) {
        //        }
        //    }
        //}
        Others otherfuncs = new Others();
        NumOfPara = otherfuncs.getFileLength(FilePath);

        AllPara = new Para[NumOfPara];
        for (int i = 0; i < NumOfPara; i++) {
            AllPara[i] = new Para();
        }


        File file2 = new File(FilePath);
        File dir = new File(file2.getParent());
        try {
            if (!dir.isDirectory()) {
                dir.mkdirs();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
        } catch (IOException e0) {
            e0.printStackTrace();
        }

        BufferedReader reader2 = null;
        try {
            reader2 = new BufferedReader(new FileReader(file2));
            String paraname = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((paraname = reader2.readLine()) != null) {
                AllPara[line - 1].loadAll(upperpath + "\\" + "F" + funcname, paraname);
                line++;
            }
            reader2.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader2 != null) {
                try {
                    reader2.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**read all para frequencies from file
     * @param upperpath path of the file of the upper FuncClass
     * @param funcname name of this object
     */
    private void readParaFreq(String upperpath, String funcname) {
        Others otherFuncs = new Others();

        //System.out.println(funcname);

        ObjectName = funcname;
        String FilePath = upperpath + "\\" + "F" + funcname + "\\" + funcname + "Parafreq.txt";
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
            String freq = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((freq = reader.readLine()) != null) {
                AllPara[line - 1].setUseFreq(otherFuncs.String2int(freq));
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

    /**read notes from file
     * @param upperpath path of the file of the upper FuncClass
     * @param funcname name of this object
     */
    private void readNotes(String upperpath, String funcname) {

        //System.out.println(funcname);

        String FilePath = upperpath + "\\" + "F" + funcname + "\\" + funcname + "notes.txt";
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

    /**read VarRangePara from file
     * @param upperPath path of the file of the upper FuncClass
     * @param funcName
     */
    private void readVarRangeParas(String upperPath, String funcName) {

        String FilePath = upperPath + "\\" + "F" + funcName + "\\" + funcName + "VarRangeParas.txt";
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
            // 一次读入一行，直到读入null为文件结束
            while ((notes = reader.readLine()) != null) {
                AllVarRangePara[NumOfVarRangePara] = new VarRangePara();
                if (notes.equals("1")) {
                    AllVarRangePara[NumOfVarRangePara].setHasStep(true);
                }
                NumOfVarRangePara++;
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

    public String getMainField() {
        return MainField;
    }

    public String getPathOfPara() {
        return UpperPath + "\\" + "F" + ObjectName + "\\" + ObjectName + "Para.txt";
    }

    public String getPathOfParaFreq() {
        return UpperPath + "\\" + "F" + ObjectName + "\\" + ObjectName + "Parafreq.txt";
    }

    public String getPathOfNotes() {
        return UpperPath + "\\" + "F" + ObjectName + "\\" + ObjectName + "notes.txt";
    }

    /**
     * output informations of this FuncClass to files
     */
    public void outputFile() {
        Others otherFuncs = new Others();
        String PathOfPara = UpperPath + "\\" + "F" + ObjectName + "\\" + ObjectName + "Para.txt";
        String PathOfParaFreq = UpperPath + "\\" + "F" + ObjectName + "\\" + ObjectName + "Parafreq.txt";
        String PathOfVarRangePara = UpperPath + "\\" + "F" + ObjectName + "\\" + ObjectName + "VarRangeParas.txt";
        for (int i = 0; i < NumOfPara; i++) {
            String ParaName = AllPara[i].getObjectName();
            String ParaFreq = "" + AllPara[i].getUseFreq();
            if (i == 0) {
                otherFuncs.writeFile(PathOfPara, ParaName, false);
                otherFuncs.writeFile(PathOfParaFreq, ParaFreq, false);
            } else {
                otherFuncs.writeFile(PathOfPara, "\r\n" + ParaName, true);
                otherFuncs.writeFile(PathOfParaFreq, "\r\n" + ParaFreq, true);
            }
            AllPara[i].outputFile();
        }
        if (NumOfPara == 0) {
            otherFuncs.writeFile(PathOfPara, "", false);
            otherFuncs.writeFile(PathOfParaFreq, "", false);
        }

        String VarRangeParaText = "";
        for (int i = 0; i < NumOfVarRangePara; i++) {
            if (AllVarRangePara[i].ifHasStep()) {
                VarRangeParaText = VarRangeParaText + 1 + "\r\n";
            } else {
                VarRangeParaText = VarRangeParaText + 0 + "\r\n";
            }
        }
        otherFuncs.writeFile(PathOfVarRangePara, VarRangeParaText, false);

        String PathOfNotes = UpperPath + "\\" + "F" + ObjectName + "\\" + ObjectName + "notes.txt";
        otherFuncs.writeFile(PathOfNotes, Notes, false);
    }

    public int isClassOrFunc() {
        return MathObject.IS_FUNC;
    }

    /**method to
     * @return
     */
    public String show() {
        String toShow = "";
        toShow = toShow + this.ObjectName + "[" + MainField;
        for (int i = 0; i < NumOfVarRangePara; i++) {
            toShow = toShow + AllVarRangePara[i].show();
        }
        for (int i = 0; i < NumOfPara; i++) {
            toShow = toShow + AllPara[i].show();
        }
        toShow = toShow + "]";
        return toShow;
    }
}