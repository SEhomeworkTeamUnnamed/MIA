package MathFunc;

/**
 * Created by ’≈Àº‘¥ on 2015/11/16.
 */
public class VarRangePara extends Para {
    String VarName;
    String VarMin;
    String VarMax;
    String VarStep;
    boolean HasStep;

    public VarRangePara(){
        super();
        VarName="";
        VarMin="";
        VarMax="";
        VarStep="";
        HasStep=false;
        setObjectName("I'm a variable! yeah!");
        setNotes("I have no notes");
    }

    public boolean IfHasStep(){
        return HasStep;
    }

    public String show(){
        if(ShowName){
            String toShow=", {";
            toShow=toShow+VarName+", "+VarMin+", "+VarMax;
            if(HasStep){
                toShow=toShow+", "+VarStep;
            }
            return toShow+"}";
        }
        return "";
    }

    public void setVarName(String varName) {
        VarName = varName;
    }

    public void setVarMin(String varMin) {
        VarMin = varMin;
    }

    public void setVarMax(String varMax) {
        VarMax = varMax;
    }

    public void setVarStep(String varStep) {
        VarStep = varStep;
    }

    public void setHasStep(boolean hasStep) {
        HasStep = hasStep;
    }
}
