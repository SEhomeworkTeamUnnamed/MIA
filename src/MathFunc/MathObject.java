package MathFunc;

/**
 * Created by IIIS on 11/1/2015.
 */
public class MathObject {
    String ObjectName;
    String UpperPath;
    String Notes;
    int UseFreq;

    public MathObject() {
        ObjectName = "";
        UpperPath = "";
        Notes = "";
        UseFreq=0;
    }

    public String getObjectName() {
        return ObjectName;
    }

    public void setObjectName(String objectName) {
        ObjectName = objectName;
    }

    public String getUpperPath() {
        return UpperPath;
    }

    public void setUpperPath(String upperPath) {
        UpperPath = upperPath;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public int getUseFreq() {
        return UseFreq;
    }

    public void setUseFreq(int useFreq) {
        UseFreq = useFreq;
    }

    public void oneUse(){UseFreq++;}
}
