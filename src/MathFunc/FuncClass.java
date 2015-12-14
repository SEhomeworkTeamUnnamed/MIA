package MathFunc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;

import Others.*;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Created by IIIS on 10/28/2015.
 *
 * @author jwt625
 */
public class FuncClass extends MathObject {

    MathFunc[] AllMathFunc;
    FuncClass[] AllFuncClass;
    String[] ClassToDelete = new String[0];
    String[] FuncToDelete = new String[0];
    int NumOfFunc;
    int NumOfClass;

    public FuncClass() {
        super();
        NumOfFunc = 0;
        NumOfClass = 0;
    }

    public FuncClass(String upperPath, String objectName) {
        this.setUpperPath(upperPath);
        this.setObjectName(objectName);
        this.readAll();
    }

    public FuncClass(String upperPath, String objectName, int i){
        super();
        this.setUpperPath(upperPath);
        this.setObjectName(objectName);
        NumOfFunc = 0;
        NumOfClass = 0;
    }

    public void print() {
        System.out.print("FuncClass name: ");
        System.out.println(ObjectName);
        System.out.print("FuncClass notes: ");
        System.out.println(Notes);
        System.out.print(ObjectName + " include class:\n");
        for (int i = 0; i < NumOfClass; i++) {
            AllFuncClass[i].print();
        }
        System.out.print(ObjectName + " include functions:\n");
        for (int i = 0; i < NumOfFunc; i++) {
            AllMathFunc[i].print();
        }
        System.out.println("------end of Class " + ObjectName);
    }

    public void setNumOfFunc(int numoffunc) {
        NumOfFunc = numoffunc;
    }

    public void setNumOfClass(int numofclass) {
        NumOfClass = numofclass;
    }

    public void setNewUpperPath(String upperPath) {

    }

    public int getNumOfFunc() {
        return NumOfFunc;
    }

    public int getNumOfClass() {
        return NumOfClass;
    }

    public int getIndexOfFunc(String FuncName) {
        for (int i = 0; i < NumOfFunc; i++) {
            if (AllMathFunc[i].getObjectName().equals(FuncName)) {
                return i;
            }
        }
        return -1;
    }

    public int getIndexOfFunc(String[] Path, String FuncName) {
        if (Path.length == 1) {
            if (ObjectName.equals(Path[0])) {
                return this.getIndexOfFunc(FuncName);
            }
        } else {

            int index = this.getIndexOfClass(Path[1]);
            if (index >= 0) {
                String[] tempPath = new String[Path.length - 1];
                for (int i = 0, len = Path.length - 1; i < len; i++) {
                    tempPath[i] = Path[i + 1];
                }
                return AllFuncClass[index].getIndexOfFunc(tempPath, FuncName);
            }
        }
        return -1;
    }

    public int getIndexOfClass(String ClassName) {
        for (int i = 0; i < NumOfClass; i++) {
            if (AllFuncClass[i].getObjectName().equals(ClassName)) {
                return i;
            }
        }
        return -1;
    }

    public int getIndexOfClass(String[] Path, String ClassName) {
        if (Path.length == 1) {
            if (ObjectName.equals(Path[0])) {
                return this.getIndexOfClass(ClassName);
            }
        } else {

            int index = this.getIndexOfClass(Path[1]);
            if (index >= 0) {
                String[] tempPath = new String[Path.length - 1];
                for (int i = 0, len = Path.length - 1; i < len; i++) {
                    tempPath[i] = Path[i + 1];
                }
                return AllFuncClass[index].getIndexOfClass(tempPath, ClassName);
            }
        }
        return -1;
    }

    public MathFunc[] getAllMathFunc() {
        return AllMathFunc;
    }

    public FuncClass[] getAllFuncClass() {
        return AllFuncClass;
    }

    public Hashtable getHashtable() {
        Hashtable tempTable = new Hashtable();
        String[] AllFuncName = new String[NumOfFunc];
        for (int i = 0; i < NumOfFunc; i++) {
            //AllFuncName[i]=AllMathFunc[i].getObjectName();
            tempTable.put(AllMathFunc[i].getObjectName(), AllMathFunc[i].getObjectName());
        }
        //tempTable.put(ObjectName,AllFuncName);
        for (int i = 0; i < NumOfClass; i++) {
            tempTable.put(AllFuncClass[i].getObjectName(), AllFuncClass[i].getHashtable());
        }
        return tempTable;
    }

    public DefaultMutableTreeNode getTreeNode() {
        DefaultMutableTreeNode tempNode = new DefaultMutableTreeNode(this);

        for (int i = 0; i < NumOfClass; i++) {
            DefaultMutableTreeNode node = AllFuncClass[i].getTreeNode();
            tempNode.add(node);
        }
        for (int i = 0; i < NumOfFunc; i++) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(AllMathFunc[i]);
            tempNode.add(node);
        }
        return tempNode;
    }

    public boolean hasSubClass() {
        if (NumOfClass == 0) {
            return false;
        }
        return true;
    }

    public boolean hasSubClass(String ClassName) {
        for (int i = 0; i < NumOfClass; i++) {
            if (ClassName.equals(AllFuncClass[i].getObjectName())) {
                return true;
            }
        }
        return false;
    }

    public boolean hasSubClass(String[] Path, String ClassName) {
        if (Path.length == 1) {
            if (ObjectName.equals(Path[0])) {
                return this.hasSubClass(ClassName);
            }
        } else {

            int index = this.getIndexOfClass(Path[1]);
            if (index >= 0) {
                String[] tempPath = new String[Path.length - 1];
                for (int i = 0, len = Path.length - 1; i < len; i++) {
                    tempPath[i] = Path[i + 1];
                }
                return AllFuncClass[index].hasSubClass(tempPath, ClassName);
            }

        }
        return false;
    }

    public boolean hasMathFunc() {
        if (NumOfFunc == 0) {
            return false;
        }
        return true;
    }

    public boolean hasMathFunc(String FuncName) {
        for (int i = 0; i < NumOfFunc; i++) {
            if (FuncName.equals(AllMathFunc[i].getObjectName())) {
                return true;
            }
        }
        return false;
    }

    public boolean hasMathFunc(String[] Path, String FuncName) {
        if (Path.length == 1) {
            if (ObjectName.equals(Path[0])) {
                return this.hasMathFunc(FuncName);
            }
        } else {

            int index = this.getIndexOfClass(Path[1]);
            if (index >= 0) {
                String[] tempPath = new String[Path.length - 1];
                for (int i = 0, len = Path.length - 1; i < len; i++) {
                    tempPath[i] = Path[i + 1];
                }
                return AllFuncClass[index].hasMathFunc(tempPath, FuncName);
            }

        }
        return false;
    }


    public void clearAll() {
        UpperPath = "";
        ObjectName = "";
        Notes = "";
        UseFreq = 0;
        AllFuncClass = null;
        AllMathFunc = null;
        NumOfFunc = 0;
        NumOfClass = 0;
        ClassToDelete = null;
        FuncToDelete = null;
    }

    public void readAll() {
        String upperpath = UpperPath;
        String classname = ObjectName;
        readFunc(upperpath, classname);
        readFuncFreq(upperpath, classname);
        readClass(upperpath, classname);
        readNotes(upperpath, classname);
    }

    public void readAll(String upperpath, String classname) {
        readFunc(upperpath, classname);
        readFuncFreq(upperpath, classname);
        readClass(upperpath, classname);
        readNotes(upperpath, classname);
    }

    private void readFunc(String upperpath, String classname) {

        //System.out.println(classname);
        UpperPath = upperpath;
        String FilePath = upperpath + "\\" + "C" + classname + "\\" + classname + "Func.txt";
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
        NumOfFunc = otherfuncs.getFileLength(FilePath);


        ObjectName = classname;
        AllMathFunc = new MathFunc[NumOfFunc];
        for (int i = 0; i < NumOfFunc; i++) {
            AllMathFunc[i] = new MathFunc();
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
            String funcname = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((funcname = reader2.readLine()) != null) {
                AllMathFunc[line - 1].readAll(upperpath + "\\" + "C" + classname, funcname);
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

    private void readFuncFreq(String upperpath, String classname) {
        Others otherFuncs = new Others();
        String FilePath = upperpath + "\\" + "C" + classname + "\\" + classname + "FuncFreq.txt";
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
                AllMathFunc[line - 1].setUseFreq(otherFuncs.String2int(freq));
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

    private void readClass(String upperpath, String classname) {

        //System.out.println(classname);

        String FilePath0 = upperpath + "\\" + "C" + classname + "\\" + "hasSubClass.txt";
        Others otherfuncs = new Others();
        if (otherfuncs.hasSubClass(FilePath0)) {

            String FilePath = upperpath + "\\" + "C" + classname + "\\" + classname + "SubClass.txt";
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
            NumOfClass = otherfuncs.getFileLength(FilePath);

            ObjectName = classname;
            AllFuncClass = new FuncClass[NumOfClass];
            for (int i = 0; i < NumOfClass; i++) {
                AllFuncClass[i] = new FuncClass();
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
                String subclassname = null;
                int line = 1;
                // 一次读入一行，直到读入null为文件结束
                while ((subclassname = reader2.readLine()) != null) {
                    AllFuncClass[line - 1].readAll(upperpath + "\\" + "C" + classname, subclassname);
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

    private void readNotes(String upperpath, String classname) {
        String FilePath = upperpath + "\\" + "C" + classname + "\\" + classname + "notes.txt";
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
                Notes = Notes + notes+"\n";
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

    public void addSubClass(FuncClass NewClass) {
        FuncClass[] tempFuncClass = new FuncClass[NumOfClass + 1];
        for (int i = 0; i < NumOfClass; i++) {
            tempFuncClass[i] = AllFuncClass[i];
        }
        AllFuncClass = null;
        AllFuncClass = tempFuncClass;
        AllFuncClass[NumOfClass] = NewClass;
        NumOfClass++;
    }

    public void addSubClass(String SubClassName) {
        FuncClass[] tempFuncClass = new FuncClass[NumOfClass + 1];
        for (int i = 0; i < NumOfClass; i++) {
            tempFuncClass[i] = AllFuncClass[i];
        }
        AllFuncClass = null;
        AllFuncClass = tempFuncClass;
        AllFuncClass[NumOfClass] = new FuncClass();
        AllFuncClass[NumOfClass].setObjectName(SubClassName);
        AllFuncClass[NumOfClass].setUpperPath(UpperPath + "\\" + "C" + ObjectName);
        NumOfClass++;
    }

    public void addSubClass(String[] Path, String SubClassName) {
        if (Path.length == 1) {
            if (ObjectName.equals(Path[0])) {
                this.addSubClass(SubClassName);
            }
        } else {
            int index = this.getIndexOfClass(Path[1]);
            if (index >= 0) {
                String[] tempPath = new String[Path.length - 1];
                for (int i = 0, len = Path.length - 1; i < len; i++) {
                    tempPath[i] = Path[i + 1];
                }
                AllFuncClass[index].addSubClass(tempPath, SubClassName);
            }
        }
    }

    public void addSubClass(String[] Path, FuncClass NewClass) {
        if (Path.length == 1) {
            if (ObjectName.equals(Path[0])) {
                this.addSubClass(NewClass);
            }
        } else {
            int index = this.getIndexOfClass(Path[1]);
            if (index >= 0) {
                String[] tempPath = new String[Path.length - 1];
                for (int i = 0, len = Path.length - 1; i < len; i++) {
                    tempPath[i] = Path[i + 1];
                }
                AllFuncClass[index].addSubClass(tempPath, NewClass);
            }
        }
    }

    public void addClassToDelete(String ClassName) {
        String[] temp = new String[ClassToDelete.length + 1];
        for (int i = 0, len = ClassToDelete.length; i < len; i++) {
            temp[i] = ClassToDelete[i];
        }
        temp[ClassToDelete.length] = ClassName;
        ClassToDelete = null;
        ClassToDelete = temp;
    }

    public void deleteSubClass(String SubClassName) {
        FuncClass[] tempFuncClass = new FuncClass[NumOfClass - 1];
        int index = this.getIndexOfClass(SubClassName);
        String classToDelete = AllFuncClass[index].getObjectName();
        if (index >= 0) {
            for (int i = 0; i < index; i++) {
                tempFuncClass[i] = AllFuncClass[i];
            }
            for (int i = index + 1; i < NumOfClass; i++) {
                tempFuncClass[i - 1] = AllFuncClass[i];
            }
            AllFuncClass = null;
            AllFuncClass = tempFuncClass;
            NumOfClass--;
            addClassToDelete(SubClassName);
            System.out.println(classToDelete+ " subclass deleted");
        }
    }

    public void deleteSubClass(String[] Path, String SubClassName) {
        if (Path.length == 1) {
            if (ObjectName.equals(Path[0])) {
                this.deleteSubClass(SubClassName);
            }
        } else {
            int index = this.getIndexOfClass(Path[1]);
            if (index >= 0) {
                String[] tempPath = new String[Path.length - 1];
                for (int i = 0, len = Path.length - 1; i < len; i++) {
                    tempPath[i] = Path[i + 1];
                }
                AllFuncClass[index].deleteSubClass(tempPath, SubClassName);
            }
        }
    }

    public void addMathFunc(MathFunc NewMathFunc) {
        MathFunc[] tempMathFunc = new MathFunc[NumOfFunc + 1];
        for (int i = 0; i < NumOfFunc; i++) {
            tempMathFunc[i] = AllMathFunc[i];
        }
        AllMathFunc = null;
        AllMathFunc = tempMathFunc;
        AllMathFunc[NumOfFunc] = NewMathFunc;
        NumOfFunc++;
    }

    public void addMathFunc(String FuncName) {
        MathFunc[] tempMathFunc = new MathFunc[NumOfFunc + 1];
        for (int i = 0; i < NumOfFunc; i++) {
            tempMathFunc[i] = AllMathFunc[i];
        }
        AllMathFunc = null;
        AllMathFunc = tempMathFunc;
        AllMathFunc[NumOfFunc] = new MathFunc();
        AllMathFunc[NumOfFunc].setObjectName(FuncName);
        AllMathFunc[NumOfFunc].setUpperPath(UpperPath + "\\" + "C" + ObjectName);
        NumOfFunc++;
    }

    public void addMathFunc(String[] Path, String FuncName) {
        if (Path.length == 1) {
            if (ObjectName.equals(Path[0])) {
                this.addMathFunc(FuncName);
            }
        } else {
            int index = this.getIndexOfClass(Path[1]);
            if (index >= 0) {
                String[] tempPath = new String[Path.length - 1];
                for (int i = 0, len = Path.length - 1; i < len; i++) {
                    tempPath[i] = Path[i + 1];
                }
                AllFuncClass[index].addMathFunc(tempPath, FuncName);
            }
        }
    }

    public void addMathFunc(String[] Path, MathFunc NewMathFunc) {
        if (Path.length == 1) {
            if (ObjectName.equals(Path[0])) {
                this.addMathFunc(NewMathFunc);
            }
        } else {
            int index = this.getIndexOfClass(Path[1]);
            if (index >= 0) {
                String[] tempPath = new String[Path.length - 1];
                for (int i = 0, len = Path.length - 1; i < len; i++) {
                    tempPath[i] = Path[i + 1];
                }
                AllFuncClass[index].addMathFunc(tempPath, NewMathFunc);
            }
        }
    }

    public void addFuncToDelete(String FuncName) {
        String[] temp = new String[FuncToDelete.length + 1];
        for (int i = 0, len = FuncToDelete.length; i < len; i++) {
            temp[i] = FuncToDelete[i];
        }
        temp[FuncToDelete.length] = FuncName;
        FuncToDelete = null;
        FuncToDelete = temp;
    }

    public void deleteMathFunc(String FuncName) {
        MathFunc[] tempMathFunc = new MathFunc[NumOfFunc - 1];
        int index = this.getIndexOfFunc(FuncName);
        String funcToDelete = AllMathFunc[index].getObjectName();
        if (index >= 0) {
            for (int i = 0; i < index; i++) {
                tempMathFunc[i] = AllMathFunc[i];
            }
            for (int i = index + 1; i < NumOfFunc; i++) {
                tempMathFunc[i - 1] = AllMathFunc[i];
            }
            AllMathFunc = null;
            AllMathFunc = tempMathFunc;
            NumOfFunc--;
            addFuncToDelete(FuncName);
            System.out.println(funcToDelete+" func deleted");
        }
    }

    public void deleteMathFunc(String[] Path, String FuncName) {
        if (Path.length == 1) {
            if (ObjectName.equals(Path[0])) {
                this.deleteMathFunc(FuncName);
            }
        } else {
            int index = this.getIndexOfClass(Path[1]);
            if (index >= 0) {
                String[] tempPath = new String[Path.length - 1];
                for (int i = 0, len = Path.length - 1; i < len; i++) {
                    tempPath[i] = Path[i + 1];
                }
                AllFuncClass[index].deleteMathFunc(tempPath, FuncName);
            }
        }
    }

    public String getPathOfFunc() {
        return UpperPath + "\\" + "C" + ObjectName + "\\" + ObjectName + "Func.txt";
    }

    public String getPathOfFuncFreq() {
        return UpperPath + "\\" + "C" + ObjectName + "\\" + ObjectName + "FuncFreq.txt";
    }

    public String getPathOfNotes() {
        return UpperPath + "\\" + "C" + ObjectName + "\\" + ObjectName + "notes.txt";
    }

    public void outputFile() {
        Others otherFuncs = new Others();
        String PathOfFunc = UpperPath + "\\" + "C" + ObjectName + "\\" + ObjectName + "Func.txt";
        String PathOfFuncFreq = UpperPath + "\\" + "C" + ObjectName + "\\" + ObjectName + "FuncFreq.txt";
        for (int i = 0; i < NumOfFunc; i++) {
            String FuncName = AllMathFunc[i].getObjectName();
            String FuncFreq = "" + AllMathFunc[i].getUseFreq();
            if (i == 0) {
                otherFuncs.writeFile(PathOfFunc, FuncName, false);
                otherFuncs.writeFile(PathOfFuncFreq, FuncFreq, false);
            } else {
                otherFuncs.writeFile(PathOfFunc, "\r\n" + FuncName, true);
                otherFuncs.writeFile(PathOfFuncFreq, "\r\n" + FuncFreq, true);
            }
            AllMathFunc[i].outputFile();
        }
        if (NumOfFunc == 0) {
            otherFuncs.writeFile(PathOfFunc, "", false);
            otherFuncs.writeFile(PathOfFuncFreq, "", false);
        }

        String PathOfSubClass = UpperPath + "\\" + "C" + ObjectName + "\\" + ObjectName + "SubClass.txt";
        for (int i = 0; i < NumOfClass; i++) {
            String ClassName = AllFuncClass[i].getObjectName();
            if (i == 0) {
                otherFuncs.writeFile(PathOfSubClass, ClassName, false);
                otherFuncs.writeFile(UpperPath + "\\" + "C" + ObjectName + "\\" + "hasSubClass.txt", "" + 1, false);
            } else {
                otherFuncs.writeFile(PathOfSubClass, "\r\n" + ClassName, true);
            }
            AllFuncClass[i].outputFile();
        }
        if (NumOfClass == 0) {
            otherFuncs.writeFile(PathOfSubClass, "", false);
            otherFuncs.writeFile(UpperPath + "\\" + "C" + ObjectName + "\\" + "hasSubClass.txt", "" + 0, false);
        }

        String PathOfNotes = UpperPath + "\\" + "C" + ObjectName + "\\" + ObjectName + "notes.txt";
        otherFuncs.writeFile(PathOfNotes, Notes, false);

        for (int i = 0, len = FuncToDelete.length; i < len; i++) {
            otherFuncs.deleteFolder(UpperPath + "\\" + "C" + ObjectName + "\\" + "F" + FuncToDelete[i]);
        }
        for (int i = 0, len = ClassToDelete.length; i < len; i++) {
            otherFuncs.deleteFolder(UpperPath + "\\" + "C" + ObjectName + "\\" + "C" + ClassToDelete[i]);
        }
    }

    public int isClassOrFunc(){
        return MathObject.IS_CLASS;
    }

}
