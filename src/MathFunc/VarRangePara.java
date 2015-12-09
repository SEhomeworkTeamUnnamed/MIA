package MathFunc;

/**
 * Created by ’≈Àº‘¥ on 2015/11/16.
 */
public class VarRangePara extends Para {
    String VarName;
    double VarMin;
    double VarMax;
    double VarStep;

    public VarRangePara(){
        super();
        VarName="";
        VarMin=0;
        VarMax=0;
        VarStep=0;
    }

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
