package Others;

import MathFunc.FuncClass;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by jwt625 on 10/31/2015.
 *
 * @author jwt625
 */

public class Test {
    public static void main(String[] args) {
        String[] t=new String [0];
        System.out.print(t.length);
        Others others=new Others();
        others.deleteFolder("C:\\Users\\IIIS\\Documents\\GitHub\\MIA\\root\\CsimpleClass\\CnewClass\\CnewClass2");



        //ReadFuncClass classReader = new ReadFuncClass();
//
        //FuncClass[] AllFuncClass = new FuncClass[10];
        //for(int i = 0; i < 10; i++){
        //    AllFuncClass[i]=new FuncClass();
        //}
        //int numOfClass = classReader.readClass(AllFuncClass);
        //AllFuncClass
        //for(int i = 0; i < numOfClass; i++){
        //    AllFuncClass[i].print();
        //}
        FuncClass RootClass=new FuncClass();
        RootClass.setUpperPath(System.getProperty("user.dir")+"\\root");
        RootClass.setObjectName("simpleClass");
        RootClass.readAll();
        //String[] path={"simpleClass"};
        //RootClass.addSubClass(path,"newClass");
        String[] path2={"simpleClass","newClass"};
        //RootClass.addSubClass(path2,"newClass2");
        RootClass.deleteSubClass(path2,"newClass2");
        //RootClass.addSubClass("Class3");
        //RootClass.addMathFunc("sb!yeah!");
        //RootClass.getAllFuncClass()[0].getAllFuncClass()[0].addSubClass("subsubclass");
        RootClass.print();
        RootClass.outputFile();
    }
}