package GUI;

import FileIO.*;
import MathFunc.*;

/**
 * Created by IIIS on 10/28/2015.
 */
public class MainPanel {
    public static void main(String[] args){
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
        RootClass.setObjectName("rootClass");
        RootClass.readAll();
        //RootClass.addSubClass("Class3");
        //RootClass.addMathFunc("sb!yeah!");
        //RootClass.getAllFuncClass()[0].getAllFuncClass()[0].addSubClass("subsubclass");
        RootClass.print();
        RootClass.outputFile();
    }

}
