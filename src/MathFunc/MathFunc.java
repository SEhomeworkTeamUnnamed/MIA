package MathFunc;

import Others.Others;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by IIIS on 10/28/2015.
 */
public class MathFunc {
    String FuncName;
    String UpperPath;
    String Notes;
    Para[] AllPara;
    int NumOfPara;
    int UseFreq;

    public MathFunc() {
        FuncName = "";
        UpperPath="";
        Notes="";
        NumOfPara = 0;
        UseFreq = 0;
    }

    public void oneUse() {
        UseFreq++;
    }

    public void setUseFreq(int freq) {
        UseFreq = freq;
    }

    public void setFuncName(String funcname) {
        FuncName = funcname;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public void setUpperPath(String upperPath) {
        UpperPath = upperPath;
    }

    public void setAllPara(Para[] NewParas) {
        NumOfPara = NewParas.length;
        for (int i = 0; i < NumOfPara; i++) {
            AllPara[i] = NewParas[i];
        }
    }

    public void addPara(Para NewPara) {
        //need modification
        Para[] tempPara = new Para[NumOfPara + 1];
        for (int i = 0; i < NumOfPara; i++) {
            tempPara[i] = AllPara[i];
        }
        AllPara = null;
        AllPara = tempPara;
        NumOfPara++;
    }

    public String getUpperPath() {
        return UpperPath;
    }

    public String getNotes() {
        return Notes;
    }

    public int getUseFreq() {
        return UseFreq;
    }

    public int getNumOfPara() {
        return NumOfPara;
    }

    public String getFuncName() {
        return FuncName;
    }

    public Para[] getAllPara() {
        return AllPara;
    }

    public void print() {
        System.out.print("\t" + FuncName + " " + UseFreq + "\n\t\tParameters:\n");
        System.out.print("\tFunc notes: ");
        System.out.println(Notes);
        for (int i = 0; i < NumOfPara; i++) {
            AllPara[i].print();
        }
    }

    public void readAll(String upperpath, String funcname) {
        readPara(upperpath, funcname);
        readParaFreq(upperpath, funcname);
        readNotes(upperpath, funcname);
    }

    public void readPara(String upperpath, String funcname) {

        //System.out.println(funcname);

        FuncName = funcname;
        UpperPath = upperpath;
        String FilePath = upperpath + "\\" + funcname + "\\" + funcname + ".txt";
        File file = new File(FilePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String paraname = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((paraname = reader.readLine()) != null) {
                NumOfPara++;
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


        AllPara = new Para[NumOfPara];
        for (int i = 0; i < NumOfPara; i++) {
            AllPara[i] = new Para();
        }


        File file2 = new File(FilePath);
        BufferedReader reader2 = null;
        try {
            reader2 = new BufferedReader(new FileReader(file2));
            String paraname = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((paraname = reader2.readLine()) != null) {
                AllPara[line - 1].loadAll(upperpath + "\\" + funcname, paraname);
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

    public void readParaFreq(String upperpath, String funcname) {
        Others otherFuncs = new Others();

        //System.out.println(funcname);

        FuncName = funcname;
        String FilePath = upperpath + "\\" + funcname + "\\" + funcname + "freq.txt";
        File file = new File(FilePath);
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

    public void readNotes(String upperpath, String funcname) {

        //System.out.println(funcname);

        String FilePath = upperpath + "\\" + funcname + "\\" + funcname + "notes.txt";
        File file = new File(FilePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String notes = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((notes = reader.readLine()) != null) {
                Notes = notes;
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