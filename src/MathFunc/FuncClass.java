package MathFunc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import Others.*;
/**
 * Created by IIIS on 10/28/2015.
 */
public class FuncClass {
    String FuncClassName;
    MathFunc[] AllMathFunc;
    FuncClass[] AllFuncClass;
    int NumOfFunc;
    int NumOfClass;

    public FuncClass(){
        FuncClassName="";
        NumOfFunc=0;
    }
    public void setFuncClassName(String classname){FuncClassName = classname;}
    public void print(){
        System.out.print("FuncClass name: ");
        System.out.println(FuncClassName);
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

    public void readFunc(String upperpath, String classname) {

        //System.out.println(classname);

        String FilePath = upperpath+"\\"+classname+"\\"+classname+".txt";
        File file = new File(FilePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String funcname = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((funcname = reader.readLine()) != null) {
                line++;
            }
            NumOfFunc = line-1;
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


        FuncClassName=classname;
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
                AllMathFunc[line-1].readPara(upperpath+"\\"+classname, funcname);
                AllMathFunc[line-1].readParaFreq(upperpath+"\\"+classname, funcname);
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
            File file = new File(FilePath);
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(file));
                String funcname = null;
                int line = 1;
                // 一次读入一行，直到读入null为文件结束
                while ((funcname = reader.readLine()) != null) {
                    line++;
                }
                NumOfClass = line - 1;
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


            FuncClassName = classname;
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
                    AllFuncClass[line - 1].readFunc(upperpath + "\\" + classname, subclassname);
                    AllFuncClass[line - 1].readFuncFreq(upperpath + "\\" + classname, subclassname);
                    AllFuncClass[line - 1].readClass(upperpath + "\\" + classname, subclassname);
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

}
