package MathFunc;

/**class describing varying range of a variable
 * Created by 张思源 on 2015/11/16.
 */
public class VarRangePara extends Para {

    /**
     * Name of the variable
     */
    String VarName;

    /**
     * minimum of the variable
     */
    String VarMin;

    /**
     * maximum of the variable
     */
    String VarMax;

    /**
     * step of the variable
     */
    String VarStep;

    /**
     * whether the variable has step or not
     */
    boolean HasStep;

    /**
     * Initialization of VarRangePara
     */
    public VarRangePara() {
        super();
        VarName = "";
        VarMin = "";
        VarMax = "";
        VarStep = "";
        HasStep = false;
        setObjectName("此处为待处理函数中的变量名称与取值范围");
        setNotes("");
    }

    /**
     * return whether the variable has step or not
     *
     * @return true if the variable has step, false for not
     */
    public boolean ifHasStep() {
        return HasStep;
    }

    public String show() {
        if (ShowName) {
            String toShow = ", {";
            toShow = toShow + VarName + ", " + VarMin + ", " + VarMax;
            if (HasStep) {
                toShow = toShow + ", " + VarStep;
            }
            return toShow + "}";
        }
        return "";
    }

    /**
     * set the name of the variable
     *
     * @param varName name to be set
     */
    public void setVarName(String varName) {
        VarName = varName;
    }

    /**
     * set the minimum of the variable
     *
     * @param varMin minimum to set
     */
    public void setVarMin(String varMin) {
        VarMin = varMin;
    }

    /**
     * set the maximum of the variable
     *
     * @param varMax maximum to set
     */
    public void setVarMax(String varMax) {
        VarMax = varMax;
    }

    /**
     * set the step of the variable, if exist
     *
     * @param varStep step to set
     */
    public void setVarStep(String varStep) {
        VarStep = varStep;
    }

    /**
     * set the variable has step or not
     *
     * @param hasStep true to set the variable has step, false for not
     */
    public void setHasStep(boolean hasStep) {
        HasStep = hasStep;
    }
}
