package readAndWrite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by David on 3/4/17.
 */
public class ReadFromFile {
    private String  fileName;
    private ArrayList<String > lines;

    public ReadFromFile(String fileName) {
        this.fileName = fileName;
        this.lines = new ArrayList<>();
    }

    public ArrayList<String> returnLine(){
        try {
            FileReader fr = new FileReader(this.fileName);
            BufferedReader br = new BufferedReader(fr);

            String str;
            while ((str = br.readLine()) != null) {
                lines.add(str);
            }

            br.close();

        } catch (IOException e) {
            System.out.println("File not found");
        }
        return lines;
    }
}
