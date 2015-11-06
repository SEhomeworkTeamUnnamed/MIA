package Others;

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
    }
}