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
        System.out.println("------end of Class " + ObjectName);
    }

    public void setNumOfFunc(int numoffunc){NumOfFunc = numoffunc;}
    public void setNumOfClass(int numofclass){NumOfClass = numofclass;}

    public int getNumOfFunc(){return NumOfFunc;}
    public int getNumOfClass(){return NumOfClass;}
    public MathFunc[] getAllMathFunc() {
        return AllMathFunc;
    }
    public FuncClass[] getAllFuncClass() {
        return AllFuncClass;
    }

    public boolean hasSubClass(){
        if(NumOfClass==0){
            return false;
        }
        return true;
    }

    public void readAll(){
        String upperpath=UpperPath;
        String classname=ObjectName;
        readFunc(upperpath, classname);
        readFuncFreq(upperpath, classname);
        readClass(upperpath, classname);
        readNotes(upperpath, classname);
    }
    public void readAll(String upperpath, String classname){
        readFunc(upperpath, classname);
        readFuncFreq(upperpath, classname);
        readClass(upperpath, classname);
        readNotes(upperpath, classname);
    }
    public void readFunc(String upperpath, String classname) {

        //System.out.println(classname);
        UpperPath=upperpath;
        String FilePath = upperpath+"\\"+classname+"\\"+classname+"Func.txt";
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
        String FilePath = upperpath+"\\"+classname+"\\"+classname+"FuncFreq.txt";
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

            String FilePath = upperpath + "\\" + classname + "\\" + classname + "SubClass.txt";
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

    public void addSubClass(FuncClass NewClass){
        FuncClass[] tempFuncClass=new FuncClass[NumOfClass+1];
        for(int i = 0; i < NumOfClass; i++){
            tempFuncClass[i]=AllFuncClass[i];
        }
        AllFuncClass=null;
        AllFuncClass=tempFuncClass;
        AllFuncClass[NumOfClass]=NewClass;
        NumOfClass++;
    }
    public void addSubClass(String SubClassName){
        FuncClass[] tempFuncClass=new FuncClass[NumOfClass+1];
        for(int i = 0; i < NumOfClass; i++){
            tempFuncClass[i]=AllFuncClass[i];
        }
        AllFuncClass=null;
        AllFuncClass=tempFuncClass;
        AllFuncClass[NumOfClass]=new FuncClass();
        AllFuncClass[NumOfClass].setObjectName(SubClassName);
        AllFuncClass[NumOfClass].setUpperPath(UpperPath+"\\"+ObjectName);
        NumOfClass++;
    }

    public void addMathFunc(MathFunc NewMathFunc){
        MathFunc[] tempMathFunc=new MathFunc[NumOfFunc+1];
        for(int i = 0; i < NumOfFunc; i++){
            tempMathFunc[i]=AllMathFunc[i];
        }
        AllMathFunc=null;
        AllMathFunc=tempMathFunc;
        AllMathFunc[NumOfFunc]=NewMathFunc;
        NumOfFunc++;
    }
    public void addMathFunc(String FuncName){
        MathFunc[] tempMathFunc=new MathFunc[NumOfFunc+1];
        for(int i = 0; i < NumOfFunc; i++){
            tempMathFunc[i]=AllMathFunc[i];
        }
        AllMathFunc=null;
        AllMathFunc=tempMathFunc;
        AllMathFunc[NumOfFunc]=new MathFunc();
        AllMathFunc[NumOfFunc].setObjectName(FuncName);
        AllMathFunc[NumOfFunc].setUpperPath(UpperPath+"\\"+ObjectName);
        NumOfFunc++;
    }
    public void outputFile(){
        Others otherFuncs=new Others();
        String PathOfFunc = UpperPath+"\\"+ObjectName+"\\"+ObjectName+"Func.txt";
        String PathOfFuncFreq = UpperPath+"\\"+ObjectName+"\\"+ObjectName+"FuncFreq.txt";
        for (int i = 0; i < NumOfFunc ; i++) {
            String FuncName = AllMathFunc[i].getObjectName();
            String FuncFreq = ""+AllMathFunc[i].getUseFreq();
            if(i==0){
                otherFuncs.writeFile(PathOfFunc, FuncName, false);
                otherFuncs.writeFile(PathOfFuncFreq, FuncFreq, false);
            }
            else{
                otherFuncs.writeFile(PathOfFunc, "\r\n"+FuncName, true);
                otherFuncs.writeFile(PathOfFuncFreq, "\r\n"+FuncFreq, true);
            }
            AllMathFunc[i].outputFile();
        }

        String PathOfSubClass = UpperPath+"\\"+ObjectName+"\\"+ObjectName+"SubClass.txt";
        for (int i = 0; i < NumOfClass; i++) {
            String ClassName = AllFuncClass[i].getObjectName();
            if(i==0){
                otherFuncs.writeFile(PathOfSubClass, ClassName, false);
                otherFuncs.writeFile(UpperPath+"\\"+ObjectName+"\\"+"hasSubClass.txt",""+1,false);
            }
            else {
                otherFuncs.writeFile(PathOfSubClass, "\r\n"+ClassName, true);
            }
            AllFuncClass[i].outputFile();
        }

        String PathOfNotes = UpperPath+"\\"+ObjectName+"\\"+ObjectName+"notes.txt";
        otherFuncs.writeFile(PathOfNotes, Notes, false);
    }
}
