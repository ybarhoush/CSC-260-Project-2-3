package readAndWrite;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by David on 3/4/17.
 */
public class OutWrite {

    private String userID;

    public OutWrite(String userID){
        this.userID = userID;

        try {
            FileWriter fw = new FileWriter("AllPlayers.txt");
            PrintWriter pw =new PrintWriter(fw);

            pw.println(userID);
            pw.close();
        } catch (IOException e){
            System.out.println("ERROR");
        }
    }

}
