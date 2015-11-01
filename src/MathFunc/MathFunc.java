package MathFunc;

import Others.Others;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by IIIS on 10/28/2015.
 */
public class MathFunc extends MathObject {

    Para[] AllPara;
    int NumOfPara;

    public MathFunc() {
        super();
        NumOfPara = 0;
    }

    public void oneUse() {
        UseFreq++;
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

    public int getNumOfPara() {
        return NumOfPara;
    }

    public Para[] getAllPara() {
        return AllPara;
    }

    public void print() {
        System.out.print("\t" + ObjectName + " " + UseFreq);
        System.out.print("\tFunc notes: ");
        System.out.print(Notes + "\n\t\tParameters:\n");
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

        ObjectName = funcname;
        UpperPath = upperpath;
        String FilePath = upperpath + "\\" + funcname + "\\" + funcname + ".txt";
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
        NumOfPara=otherfuncs.getFileLength(FilePath);

        AllPara = new Para[NumOfPara];
        for (int i = 0; i < NumOfPara; i++) {
            AllPara[i] = new Para();
        }


        File file2 = new File(FilePath);
        File dir = new File(file2.getParent());
        try{
            if (!dir.isDirectory()) {
                dir.mkdirs();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
        }catch (IOException e0) {
            e0.printStackTrace();
        }

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

        ObjectName = funcname;
        String FilePath = upperpath + "\\" + funcname + "\\" + funcname + "freq.txt";
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