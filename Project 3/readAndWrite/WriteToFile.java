package readAndWrite;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by David on 3/4/17.
 */
public class WriteToFile {


    public WriteToFile(String fileName, String lineToAdd, boolean fileExists){

        try (FileWriter fw = new FileWriter("Players/" + fileName,fileExists);
            BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw))
        {
            pw.println(lineToAdd);
            pw.close();
        } catch (IOException e){
            System.out.println("ERROR");
        }
    }

}
