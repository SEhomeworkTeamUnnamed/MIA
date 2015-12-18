package MathFunc;

/**Class to describe
 * Created by jwt625 on 11/1/2015.
 *
 * @author jwt625
 */
public class MathObject {

    /**
     * name of this object
     */
    String ObjectName;

    /**
     * path of the file of the upper FuncClass
     */
    String UpperPath;

    /**
     * notes of this object
     */
    String Notes;

    /**
     * use frequency of this object
     */
    int UseFreq;

    public static int IS_CLASS=0;

    public static int IS_FUNC=1;

    /**
     * default method to create an empty object
     */
    public MathObject() {
        ObjectName = "";
        UpperPath = "";
        Notes = "";
        UseFreq=0;
    }

    /**
     * @return name of this object
     */
    public String getObjectName() {
        return ObjectName;
    }

    /**set name of this object
     * @param objectName name to set
     */
    public void setObjectName(String objectName) {
        ObjectName = objectName;
    }

    /**
     * @return upperPath of this object
     */
    public String getUpperPath() {
        return UpperPath;
    }

    /**set upperPath
     * @param upperPath upperPath to set
     */
    public void setUpperPath(String upperPath) {
        UpperPath = upperPath;
    }

    /**
     * @return notes of this object
     */
    public String getNotes() {
        return Notes;
    }

    /**set notes
     * @param notes notes to set
     */
    public void setNotes(String notes) {
        Notes = notes;
    }

    /**
     * @return frequency of this object
     */
    public int getUseFreq() {
        return UseFreq;
    }

    /**set use frequency of this object
     * @param useFreq frequency to set
     */
    public void setUseFreq(int useFreq) {
        UseFreq = useFreq;
    }

    public void oneUse(){UseFreq++;}

    /**
     * @return name of this object
     */
    public String toString(){
        return ObjectName;
    }

    /**
     * @return command part of this object
     */
    public String show(){
        return "";
    }
}
