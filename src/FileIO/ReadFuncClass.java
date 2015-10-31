package FileIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import MathFunc.*;

/**
 * Created by IIIS on 10/28/2015.
 */
public class ReadFuncClass {
    String FilePath;
    public ReadFuncClass(){FilePath = System.getProperty("user.dir")+"\\"+"FuncClass.txt";}
    public ReadFuncClass(String FileName){FilePath = System.getProperty("user.dir")+FileName;}
    public void changeFileName(String NewFileName){FilePath = System.getProperty("user.dir")+NewFileName;}
    public String getFilePath(){return FilePath;}

    public int readClass(FuncClass[] AllFuncClass) {

        //System.out.println(FilePath);

        File file = new File(FilePath);
        BufferedReader reader = null;
        int line = 1;
        try {
            reader = new BufferedReader(new FileReader(file));
            String classname = null;
            // 一次读入一行，直到读入null为文件结束
            while ((classname = reader.readLine()) != null) {

                //System.out.println("classname = reader.readLine()) != null");
                //AllFuncClass[line-1].print();
                AllFuncClass[line-1].readFunc(System.getProperty("user.dir"),classname);
                AllFuncClass[line-1].readFuncFreq(System.getProperty("user.dir"),classname);
                AllFuncClass[line-1].readClass(System.getProperty("user.dir"),classname);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return line-1;
    }
}
