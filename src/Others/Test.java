package Others;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by jwt625 on 10/31/2015.
 */

public class Test {
    public static void main(String[] args) {

        File file = new File("D:\\testtest\\newfile.txt");
        File dir = new File(file.getParent());
        System.out.print(file.getParent());
        String content = "\r\nThis is the new text content";

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
        try (FileOutputStream fop = new FileOutputStream(file,true)) {

            // if file doesn't exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            // get the content in bytes
            byte[] contentInBytes = content.getBytes();

            fop.write(contentInBytes);
            fop.flush();
            fop.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}