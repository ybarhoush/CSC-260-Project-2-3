package readAndWrite;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Given a file name. This class will write to an existing txt file or
 * create a new one if the given file name doesn't exist and write to there.
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
