package MathFunc;

/**
 * Created by ’≈Àº‘¥ on 2015/11/16.
 */
public class VarRange extends Para {
    String VarName;
    double VarMin;
    double VarMax;
    double VarStep;

    public String show(){
        if(ShowName){
            String toShow="{";
            toShow=toShow+VarName+","+VarMin+","+VarMax;
            if(VarStep!=0){
                toShow=toShow+","+VarStep;
            }
            return toShow+"}";
        }
        return "";
    }
}
