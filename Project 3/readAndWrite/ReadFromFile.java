package readAndWrite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Given a file name. This class will read all and return the
 * data written in the given file name in the Players package
 */
public class ReadFromFile {
    private String  fileName;
    private ArrayList<String > strings;

    /**
     * @param fileName file to be read from
     */
    public ReadFromFile(String fileName) {
        this.fileName = "Players/" + fileName;
        this.strings = new ArrayList<>();
    }

    /**
     * List of strings, each string is data from one line in the txt file
     * @return array list, each line is an element
     */
    public ArrayList<String> returnStrings(){
        try {
            FileReader fr = new FileReader(this.fileName);
            BufferedReader br = new BufferedReader(fr);

            String str;
            while ((str = br.readLine()) != null) {
                strings.add(str);
            }

            br.close();

        } catch (IOException e) {
            System.out.println("File not found");
        }
        return strings;
    }
}
