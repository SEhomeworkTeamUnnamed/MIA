package MathFunc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import Others.*;
/**
 * Created by IIIS on 10/28/2015.
 */
public class FuncClass extends MathObject {

    MathFunc[] AllMathFunc;
    FuncClass[] AllFuncClass;
    int NumOfFunc;
    int NumOfClass;

    public FuncClass(){
        super();
        NumOfFunc=0;
        NumOfClass=0;
    }
    public void print(){
        System.out.print("FuncClass name: ");
        System.out.println(ObjectName);
        System.out.print("FuncClass notes: ");
        System.out.println(Notes);
        System.out.print("include class:\n");
        for(int i = 0; i < NumOfClass; i++){
            AllFuncClass[i].print();
        }
        System.out.print("include functions:\n");
        for(int i = 0; i < NumOfFunc; i++){
            AllMathFunc[i].print();
        }
    }

    public void setNumOfFunc(int numoffunc){NumOfFunc = numoffunc;}
    public void setNumOfClass(int numofclass){NumOfClass = numofclass;}

    public int getNumOfFunc(){return NumOfFunc;}
    public int getNumOfClass(){return NumOfClass;}

    public void readAll(String upperpath, String classname){
        readFunc(upperpath, classname);
        readFuncFreq(upperpath, classname);
        readClass(upperpath, classname);
        readNotes(upperpath, classname);
    }
    public void readFunc(String upperpath, String classname) {

        //System.out.println(classname);
        UpperPath=upperpath;
        String FilePath = upperpath+"\\"+classname+"\\"+classname+".txt";
        //File file = new File(FilePath);
        //BufferedReader reader = null;
        //try {
        //    reader = new BufferedReader(new FileReader(file));
        //    String funcname = null;
        //    int line = 1;
        //    // 一次读入一行，直到读入null为文件结束
        //    while ((funcname = reader.readLine()) != null) {
        //        line++;
        //    }
        //    NumOfFunc = line-1;
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
        NumOfFunc=otherfuncs.getFileLength(FilePath);


        ObjectName=classname;
        AllMathFunc=new MathFunc[NumOfFunc];
        for(int i = 0; i < NumOfFunc; i++){
            AllMathFunc[i]=new MathFunc();
        }
        File file2 = new File(FilePath);
        BufferedReader reader2 = null;
        try {
            reader2 = new BufferedReader(new FileReader(file2));
            String funcname = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((funcname = reader2.readLine()) != null) {
                AllMathFunc[line-1].readAll(upperpath+"\\"+classname, funcname);
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
    public void readFuncFreq(String upperpath, String classname){
        Others otherFuncs=new Others();
        String FilePath = upperpath+"\\"+classname+"\\"+classname+"freq.txt";
        File file = new File(FilePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String freq = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((freq = reader.readLine()) != null) {
                AllMathFunc[line-1].setUseFreq(otherFuncs.String2int(freq));
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

    public void readClass(String upperpath, String classname) {

        //System.out.println(classname);

        String FilePath0 = upperpath+"\\"+classname+"\\"+"hasSubClass.txt";
        Others otherfuncs = new Others();
        if(otherfuncs.hasSubClass(FilePath0)) {

            String FilePath = upperpath + "\\" + classname + "\\" + classname + "subClass.txt";
            //File file = new File(FilePath);
            //BufferedReader reader = null;
            //try {
            //    reader = new BufferedReader(new FileReader(file));
            //    String funcname = null;
            //    int line = 1;
            //    // 一次读入一行，直到读入null为文件结束
            //    while ((funcname = reader.readLine()) != null) {
            //        line++;
            //    }
            //    NumOfClass = line - 1;
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
            NumOfClass=otherfuncs.getFileLength(FilePath);

            ObjectName = classname;
            AllFuncClass = new FuncClass[NumOfClass];
            for (int i = 0; i < NumOfClass; i++) {
                AllFuncClass[i] = new FuncClass();
            }

            File file2 = new File(FilePath);
            BufferedReader reader2 = null;
            try {
                reader2 = new BufferedReader(new FileReader(file2));
                String subclassname = null;
                int line = 1;
                // 一次读入一行，直到读入null为文件结束
                while ((subclassname = reader2.readLine()) != null) {
                    AllFuncClass[line - 1].readAll(upperpath + "\\" + classname, subclassname);
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
    }

    public void readNotes(String upperpath, String classname){
        String FilePath = upperpath+"\\"+classname+"\\"+classname+"notes.txt";
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

    public void addSubClass(String nameOfSubClass){
        String pathOfhasSubClass=UpperPath+"\\"+ObjectName+"\\"+"hasSubClass.txt";
        String pathOfSubClass=UpperPath+"\\"+ObjectName+"\\"+ObjectName+"subClass.txt";

    }
}
