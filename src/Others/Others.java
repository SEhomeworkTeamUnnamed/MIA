package Others;

import java.io.*;

/**
 * Created by IIIS on 10/29/2015.
 */
public class Others {
    //This function returns the int value of a String of decimal number
    public int String2int(String StrToConvert){
        int len = StrToConvert.length();
        int num = 0;
        for(int i = 0; i < len; i++){
            int power = 1;
            for(int j = len; j > i+1; j--){
                power = power*10;
            }
            num = num + (StrToConvert.charAt(i)-'0')*power;
        }
        return num;
    }
    public boolean hasSubClass(String FilePath){

        File file = new File(FilePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String funcname = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((funcname = reader.readLine()) != null) {
                line++;
                if(funcname.charAt(0)=='1') {
                    return true;
                }
                else {
                    return false;
                }

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
        return false;
    }
    public void writeFile(String path, String toWrite, boolean append){

        File file = new File(path);
        File dir = new File(file.getParent());
        //System.out.print(file.getParent());
        String content = toWrite;

        try{
            if (!dir.isDirectory()) {
                dir.mkdir();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        }catch (IOException e0) {
            e0.printStackTrace();
        }
        try (FileOutputStream fop = new FileOutputStream(file, append)) {

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // get the content in bytes
            byte[] contentInBytes = content.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

            //System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args){
        Others b=new Others();
        String test = "123222";
        int a;
        a= b.String2int(test);
        System.out.print(a);
        //b.writeFile("D:/tteesstt/test.txt","test hahahaha!",false);
    }
}
