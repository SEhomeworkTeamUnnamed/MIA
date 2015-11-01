package Others;

import java.io.*;

/**
 * Created by IIIS on 10/29/2015.
 */
public class Others {
    //This function returns the int value of a String of decimal number

    /**
     * get the int value of a string comprised of decimal digits
     *      might be meaningless if given a string with characters or something else
     *
     * @param StrToConvert a string comprised of decimal digits
     * @return int value of the string
     */
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

    /**
     *get whether the class has a subclass or not
     *
     * @param FilePath path of the file recording whether the class has subclass or not
     * @return true if the class has subclass according to file
     *          at FilePath
     */
    public boolean hasSubClass(String FilePath){

        File file = new File(FilePath);
        File dir = new File(file.getParent());
        try{
            if (!dir.isDirectory()) {
                dir.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        }catch (IOException e0) {
            e0.printStackTrace();
        }
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

    /**
     * get the length of the file in path
     *
     * @param path path of the file to read
     * @return length of the file, -1 if not found
     */
    public int getFileLength(String path){
        File file = new File(path);
        File dir = new File(file.getParent());
        int line = 1;
        if (!dir.isDirectory()||!file.exists()) {
            return 0;
        }
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String str = null;
            // 一次读入一行，直到读入null为文件结束
            while ((str = reader.readLine()) != null) {
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

    /**
     * remember to add "\r\n" in param toWrite to start a new line!
     *
     * @param path path of the file to write, create new if not found
     * @param toWrite String that is going to be write into the file
     * @param append if <code>true</code>, then bytes will be written
     *                   to the end of the file rather than the beginning
     */
    public void writeFile(String path, String toWrite, boolean append){

        File file = new File(path);
        File dir = new File(file.getParent());
        //System.out.print(file.getParent());
        String content = toWrite;

        try{
            if (!dir.isDirectory()) {
                dir.mkdirs();
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

    /**
     * for testing
     *
     * @param args
     */
    public static void main(String[] args){
        Others b=new Others();
        String test = "123222";
        int a;
        a= b.String2int(test);
        System.out.print(a);
        b.writeFile("D:\\tteesstt\\test.txt","\r\ntest hahahaha!",true);
        System.out.print("\n"+b.getFileLength("D:\\tteesstt\\test.txt")+"\n");
    }
}
