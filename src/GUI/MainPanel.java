package GUI;

import FileIO.*;
import MathFunc.*;

/**
 * Created by IIIS on 10/28/2015.
 */
public class MainPanel {
    public static void main(String[] args){
        ReadFuncClass classReader = new ReadFuncClass();

        FuncClass[] AllFuncClass = new FuncClass[10];
        for(int i = 0; i < 10; i++){
            AllFuncClass[i]=new FuncClass();
        }
        int numOfClass = classReader.readClass(AllFuncClass);
        for(int i = 0; i < numOfClass; i++){
            AllFuncClass[i].print();
        }
    }

}
